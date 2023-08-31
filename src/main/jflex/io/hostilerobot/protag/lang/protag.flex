package io.hostilerobot.protag.lang;

import java.util.ArrayDeque;import java.util.Deque;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;

%%
%public
%class ProtagLexer
%unicode
%cupsym ProtagSymbols
%extends ProtagSymbols
//%type IElementType
%cup
%char
%line
%column
%{
StringBuffer current = new StringBuffer();
ComplexSymbolFactory symbolFactory;
public ProtagLexer(java.io.Reader in, ComplexSymbolFactory sf) {
    this(in);
    this.symbolFactory = sf;
}

// ProtagTokenType x Location -> Symbol
// ProtagTokenType x String x Location
private Symbol symbol( ProtagSymbolType tokenType, CharSequence payload ) {

}

enum PathContext{
      // base is used when we're not actively in a yapping segment.
      BASE(YYINITIAL, YYINITIAL),
      FILE(AFTER_SLASH, BEFORE_SLASH),
      JAVA(AFTER_JSCOPE, BEFORE_JSCOPE),
      YAPPING(AFTER_YSCOPE, BEFORE_YSCOPE);
      private final int fallback;
      private final int initialState;
      PathContext(int initialState, int fallback) {
          this.initialState = initialState;
          this.fallback = fallback;
      }
}
// we want to handle java paths !com.user.Plugin
// different from file paths @file/user/pugin.yap
// different from YAPPING paths { ... }.my-value.3.length
private Deque<PathContext> context = new ArrayDeque<>();
{
  // top level is all BASE
  // this item should be here throughout all lexing
  context.push(PathContext.BASE);
}

private void reset() {
    context.clear();
    context.push(PathContext.BASE);
    yybegin(YYINITIAL);
}

private String getStateName(int state) {
    switch(state) {
        case AFTER_JSCOPE: return "AFTER_JSCOPE";
        case AFTER_SLASH: return "AFTER_SLASH";
        case AFTER_YSCOPE: return "AFTER_YSCOPE";
        case BEFORE_JSCOPE: return "BEFORE_JSCOPE";
        case BEFORE_SLASH: return "BEFORE_SLASH";
        case BEFORE_YSCOPE: return "BEFORE_YSCOPE";
        case PENDING_JSCOPE: return "PENDING_JSCOPE";
        case PENDING_SLASH: return "PENDING_SLASH";
        case PENDING_YSCOPE: return "PENDING_YSCOPE";
        case LITERAL: return "LITERAL";
        case REGEX: return "REGEX";
        case REGEX_CLASS: return "REGEX_CLASS";
        case YYINITIAL: return "YYINITIAL";
        default: return "ERROR_UNKNOWN_STATE";
    }
}

private void debugCurrentState(String methodName) {
    /*System.out.println(
        methodName + ": " + getStateName(yystate()) + ": " + context
    );*/
}

private int getCurrentFallback() {
    debugCurrentState("getCurrentFallback");
    return context.peek().fallback;
}
// file path interrupts the current segment and starts a new one that's file
// @asdf!asdf asdf!asdf !asdf !asdf
private void encounterFilePath() {
    debugCurrentState("START encounterFilePath");
    endTransition();
    context.push(PathContext.FILE);
    yybegin(PathContext.FILE.initialState);
    debugCurrentState("END encounterFilePath");
}
private void encounterJavaPath() {
    debugCurrentState("START encounterJavaPath");
    endTransition();
    context.push(PathContext.JAVA);
    yybegin(PathContext.JAVA.initialState);
    debugCurrentState("END encounterJavaPath");
}

// new path that starts with a regex
private void encounterRegexSegment() {
    debugCurrentState("START encounterRegexSegment");
    endTransition();
    context.push(PathContext.YAPPING);
    yybegin(REGEX);
    debugCurrentState("END encounterRegexSegment");
}
// new path that starts with a literal
// !asdf "asdf"
//       ^
private void encounterLiteralSegment() {
    debugCurrentState("START encounterLiteralSegment");
    endTransition();
    context.push(PathContext.YAPPING);
    yybegin(LITERAL);
    debugCurrentState("END encounterLiteralSegment");
}

// open parentheses on a new yapping path, e.g. [ ( {
// asdf ( .. )
//      ^
// !asdf ( .. )
//       ^
private void encounterOpenSegment() {
    debugCurrentState("START encounterOpenSegment");
    endTransition();
    context.push(PathContext.BASE);
    yybegin(PathContext.BASE.initialState);
    debugCurrentState("END encounterOpenSegment");
}

// asdf asdf
//      ^
// !asdf asdf
//       ^
private void encounterYappingPath() {
    debugCurrentState("START encounterYappingPath");
    endTransition();
    context.push(PathContext.YAPPING);
    yybegin(PathContext.YAPPING.fallback);
    debugCurrentState("END encounterYappingPath");
}

// !asdf=asdf
//      ^
// @asdf, asdf
//      ^
private void endCurrentPath() {
    debugCurrentState("START endCurrentPath");
    endTransition();
    yybegin(PathContext.BASE.initialState);
    debugCurrentState("END endCurrentPath");
}

// ends the current segment
private void endTransition() {
    // encounter a start of a new yapping segment after PENDING
    if(context.size() > 1 && context.peek() != PathContext.BASE) {
        // !asdf.asdf  =  asdf
        // [F, B]     [B] [Y, B]
        context.pop();
        if(context.peek() != PathContext.BASE) {
            reset();
            // should never happen but we check anyway
            throw new IllegalStateException("Expected BASE instead found " + context.peek());
        }
    }
}

private void openCurrentSegment() {
    debugCurrentState("START openCurrentSegment");
    // encounter a (, {, or [ while in a segment
    context.push(PathContext.BASE);
    if(yystate() != PathContext.BASE.initialState) {
        yybegin(PathContext.BASE.initialState);
    }
    debugCurrentState("END openCurrentSegment");
}
private void closeCurrentSegment() {
    debugCurrentState("START closeCurrentSegment");
    // encounter ), ], or }
    if(context.size() > 1 && context.peek() == PathContext.BASE) {
        context.pop();
        // !sadf[asdf]
        //           ^
        // sadf[[asdf]]
        //           ^^
        PathContext contextResult = context.peek();
        yybegin(contextResult.fallback);
    } else if(context.size() > 2 && context.peek() != PathContext.BASE) {
        // !sa/df[!asdf]as/df
        //             ^
        context.pop(); // stop the current java or file path
        // context.size() > 1
        if(context.peek() != PathContext.BASE) {
            reset();
            // should never happen but we check anyway
            throw new IllegalStateException("Expected BASE instead found " + context.peek());
        }
        // remove this context - it corresponds to the opening brace which has now just ended
        context.pop();
        // context.size() > 0

        PathContext contextResult = context.peek();
        // we return to this context
        yybegin(contextResult.fallback);
    } else {
        // will only occur if we have a sequence with
        // sadf] or !asdf]
        if(context.size() > 1) {
            context.pop();
        }
        yybegin(PathContext.YAPPING.fallback);
    }
    // todo - should we throw if we encounter mismatched segments?
    //        ex: asdf)asdf
    //        currently we just fall back to YAPPING and let the parser deal with it
    debugCurrentState("END closeCurrentSegment");
}

private static String zzToPrintable(CharSequence cs) {
    return zzToPrintable(cs.toString());
}
%}

%eof{  return;
%eof}
LineTerminator = \r|\n|\r\n
//InputCharacter = [^\r\n\t\f\ \#]
WHITESPACE     = ({LineTerminator} | [ \t\f])+
COMMENT        = "#"[^\r\n]*{LineTerminator}?

FNAMECHAR      = [^\/\"\`\!\@\,\r\n\t\f\=\:\;\ \#\[\]\(\)\{\}\<\>]
// permit hyphens in file names, but stop short of "->"
// (asf-adf-)/
// (asf-adf)->
// (asf-adf-)->
// last segment matches - but not ->
// ({FNAMECHAR}|\-)+ "->"
// ({FNAMECHAR}|\-)+ "-----"->
// todo - have chars matching (FNAMECHAR|-)+, vs (FNAMECHAR|-)->
// latter - roll back two chars
FNAME          = {FNAMECHAR}+
FNAME_ENDARROW = ({FNAMECHAR}+){TRANSITION_SEP_R}
//({FNAMECHAR}|(\-({FNAMECHAR}|\-)))+ (!(!\-|{TRANSITION_SEP_R}))?
// !{FNAMECHAR} = a [\/\"\`\!\@\r\n\t\f\-\ \#\[\]\(\)\{\}\<\>] [^]*
//
YBODYCHAR      = //[[{FNAMECHAR}] && [^\&\+\'\$\|\~\*\^\%\\\?\%\.]]
    [^\/\"\`\!\@\,\r\n\t\f\=\-\:\;\ \#\[\]\(\)\{\}\<\>\&\+\'\$\|\~\*\^\%\\\?\%\.]
YFIRSTCHAR     = //[{YFIRSTCHAR} && [^0-9]]
    [^\/\"\`\!\@\,\r\n\t\f\=\-\:\;\ \#\[\]\(\)\{\}\<\>\&\+\'\$\|\~\*\^\%\\\?\%\.0-9]
//YFIRSTCHAR     = [^\/\"\'\`\!\@\r\n\t\f\ \#\[\]\(\)\{\}\<\>\:\&\+\;\|\=\,\~\*\^\%\\\?\%\.[0-9]]
//YBODYCHAR      = [^\/\"\'\`\!\@\r\n\t\f\ \#\[\]\(\)\{\}\<\>\:\&\-\+\;\|\=\,\~\*\^\%\\\?\%\.]
YBODY          = ({YBODYCHAR}|(\-{YFIRSTCHAR}))+    // asdf10hij maker-space10 10-asdf
YNAME          = {YFIRSTCHAR}(({YBODYCHAR}|(\-{YFIRSTCHAR}))*) // sadfasdf asdf10 x10-asdf
PLUS=\+
MINUS=\-
DOT=\.
NATURAL=[0-9]+
AND=\&
LIST_START=\[
LIST_END=\]
PRECEDENCE_START=\(
PRECEDENCE_END=\)
MAP_START=\{
MAP_END=\}
PAIR_SEP=\=
LIST_SEP=\,
JPATH_START=\@
FPATH_START=\!
SLASH=\/
PROPERTY_SEP=\:
LITERAL_QUOTE=\"
REGEX_QUOTE=\`
TRANSITION_SEP_L="<-"
TRANSITION_SEP_R="->"
%state LITERAL
%state REGEX
%state REGEX_CLASS
%state BEFORE_SLASH, AFTER_SLASH, PENDING_SLASH
%state BEFORE_JSCOPE, AFTER_JSCOPE, PENDING_JSCOPE
%state BEFORE_YSCOPE, AFTER_YSCOPE, PENDING_YSCOPE

%%

// we have found a new identifier.

// DEFAULT 1: new identifier stops previous path
<PENDING_SLASH, PENDING_JSCOPE, PENDING_YSCOPE> {
    {LITERAL_QUOTE}                                 {
                                                        // we are encountering a new literal
                                                        encounterLiteralSegment();
                                                    }
    {REGEX_QUOTE}                                   {
                                                        encounterRegexSegment();
                                                    }
    {YNAME}                                         {
                                                        encounterYappingPath();
                                                        return YappingTypes.YNAME;
                                                    }
    {NATURAL}                                       {
                                                        encounterYappingPath();
                                                        return YappingTypes.NATURAL;
                                                    }
    {MAP_START}                                     {
                                                        encounterOpenSegment();
                                                        return YappingTypes.MAP_START;
                                                    }
    {MAP_END}                                       {
                                                        closeCurrentSegment();
                                                        return YappingTypes.MAP_END;
                                                    }
    {LIST_START}                                    {
                                                        encounterOpenSegment();
                                                        return YappingTypes.LIST_START;
                                                    }
    {LIST_END}                                      {
                                                        closeCurrentSegment();
                                                        return YappingTypes.LIST_END;
                                                    }
    {PRECEDENCE_START}                              {
                                                        encounterOpenSegment();
                                                        return YappingTypes.PRECEDENCE_START;
                                                    }
    {PRECEDENCE_END}                                {
                                                        closeCurrentSegment();
                                                        return YappingTypes.PRECEDENCE_END;
                                                    }
}

// DEFAULT 2: new java|file path stops previous path
//            and binary operators: items that may end the current path
<YYINITIAL, PENDING_SLASH, PENDING_JSCOPE, PENDING_YSCOPE, BEFORE_SLASH, BEFORE_JSCOPE, BEFORE_YSCOPE> {
    {FPATH_START}                                   {
                                                        /* fall back to BEFORE_SLASH when we're done parsing regex or literal */
                                                        encounterFilePath();
                                                        return YappingTypes.FPATH_START;
                                                    }
    {JPATH_START}                                   {
                                                        encounterJavaPath();
                                                        return YappingTypes.JPATH_START;
                                                    }
    {TRANSITION_SEP_L}                              {
                                                        endCurrentPath();
                                                        return YappingTypes.TRANSITION_SEP_L;
                                                    }
    {TRANSITION_SEP_R}                              {
                                                        endCurrentPath();
                                                        return YappingTypes.TRANSITION_SEP_R;
                                                    }
    {PAIR_SEP}                                      {
                                                        endCurrentPath();
                                                        return YappingTypes.PAIR_SEP;
                                                    }
    {LIST_SEP}                                      {
                                                        endCurrentPath();
                                                        return YappingTypes.LIST_SEP;
                                                    }
    {PROPERTY_SEP}                                  {
                                                        endCurrentPath();
                                                        return YappingTypes.PROPERTY_SEP;
                                                    }
}
// DEFAULT 3: whitespace, comments, etc in yapping context
<YYINITIAL> {
    {COMMENT}                                       { return YappingTypes.COMMENT; }
    {WHITESPACE}                                    { return YappingTypes.WHITESPACE; }
    // parts in yapping paths
    {YNAME}                                         {
                                                        encounterYappingPath();
                                                        return YappingTypes.YNAME;
                                                    }
    {NATURAL}                                       {
                                                        encounterYappingPath();
                                                        return YappingTypes.NATURAL;
                                                    }

    {REGEX_QUOTE}                                   {
                                                        encounterRegexSegment();
                                                    }

    {LITERAL_QUOTE}                                 {
                                                        encounterLiteralSegment();
                                                    }

    {DOT}                                           { return YappingTypes.DOT; }
    {SLASH}                                         { return YappingTypes.SLASH; }
}
// DEFAULT 4: items in a path that can be followed by . or /
<YYINITIAL, BEFORE_SLASH, BEFORE_JSCOPE, BEFORE_YSCOPE, AFTER_JSCOPE, AFTER_SLASH, AFTER_YSCOPE> {
    {MAP_START}                                     {
                                                        openCurrentSegment();
                                                        return YappingTypes.MAP_START;
                                                    }
    {MAP_END}                                       {
                                                        closeCurrentSegment();
                                                        return YappingTypes.MAP_END;
                                                    }
    {LIST_START}                                    {
                                                        openCurrentSegment();
                                                        return YappingTypes.LIST_START;
                                                    }
    {LIST_END}                                      {
                                                        closeCurrentSegment();
                                                        return YappingTypes.LIST_END;
                                                    }
    {PRECEDENCE_START}                              {
                                                        openCurrentSegment();
                                                        return YappingTypes.PRECEDENCE_START;
                                                    }
    {PRECEDENCE_END}                                {
                                                        closeCurrentSegment();
                                                        return YappingTypes.PRECEDENCE_END;
                                                    }
}
<BEFORE_SLASH, BEFORE_JSCOPE, BEFORE_YSCOPE, AFTER_JSCOPE, AFTER_SLASH, AFTER_YSCOPE> {
    // if have another token that can be used in the path, then use it
    // after REGEX or LITERAL are parsed. Will fall back to BEFORE_(SCOPE|SLASH)
    {REGEX_QUOTE}                                   {yybegin(REGEX);}
    {LITERAL_QUOTE}                                 {yybegin(LITERAL);}
}
// DEFAULT 5: items that may be in a file path but not in a java path/yapping path
<YYINITIAL, BEFORE_YSCOPE, BEFORE_JSCOPE, PENDING_YSCOPE, PENDING_JSCOPE, PENDING_SLASH> {
    {AND}                                           {
                                                        endCurrentPath();
                                                        return YappingTypes.AND;
                                                    }
    {PLUS}                                          {
                                                        endCurrentPath();
                                                        return YappingTypes.PLUS;
                                                    }
    {MINUS}                                         {
                                                        endCurrentPath();
                                                        return YappingTypes.MINUS;
                                                    }
}
<BEFORE_YSCOPE, BEFORE_JSCOPE, PENDING_YSCOPE, PENDING_JSCOPE> {
    {SLASH}                                         {
                                                        endCurrentPath();
                                                        return YappingTypes.SLASH;
                                                    }
}


<BEFORE_SLASH> {
    // encounter a file name, state doesn't change.
    {FNAME}                                         {return YappingTypes.FNAME;}
    {FNAME_ENDARROW}                                {
          yypushback(2);
          return YappingTypes.FNAME;
      }

    // encountered a slash - we may now have whitespace
    {SLASH}                                         {yybegin(AFTER_SLASH); return YappingTypes.SLASH; }
    // pending slash - if we encounter another / we are still parsing file path. otherwise we are parsing a new item.
    {COMMENT}                                       {yybegin(PENDING_SLASH); return YappingTypes.COMMENT;}
    {WHITESPACE}                                    {yybegin(PENDING_SLASH); return YappingTypes.WHITESPACE;}
    // other valid tokens are covered in DEFAULT 2
}
<BEFORE_JSCOPE> {
    [:jletterdigit:]+                               {return YappingTypes.JBODY;}
    // encountered a scope - we may now have whitespace
    {DOT}                                           {yybegin(AFTER_JSCOPE); return YappingTypes.DOT; }
    // pending slash - if we encounter another / we are still parsing file path. otherwise we are parsing a new item.
    {COMMENT}                                       {yybegin(PENDING_JSCOPE); return YappingTypes.COMMENT;}
    {WHITESPACE}                                    {yybegin(PENDING_JSCOPE); return YappingTypes.WHITESPACE;}
    // other valid tokens are covered in DEFAULT 2
}
<BEFORE_YSCOPE> {
    {NATURAL}                                       {return YappingTypes.NATURAL;}
    {YBODY}                                         {return YappingTypes.YBODY;}

    {DOT}                                           {yybegin(AFTER_YSCOPE); return YappingTypes.DOT; }

    {COMMENT}                                       {yybegin(PENDING_YSCOPE); return YappingTypes.COMMENT; }
    {WHITESPACE}                                    {yybegin(PENDING_YSCOPE); return YappingTypes.WHITESPACE; }
}
<AFTER_SLASH, AFTER_JSCOPE, AFTER_YSCOPE, PENDING_JSCOPE, PENDING_SLASH, PENDING_YSCOPE> {
    // after or pending a scope separator: comments and or whitespace will stay in the same state
    {WHITESPACE}                                    {return YappingTypes.WHITESPACE;}
    {COMMENT}                                       {return YappingTypes.COMMENT;}
}
<AFTER_SLASH> {
    // file//asdf is fine
    {SLASH}                                         {return YappingTypes.SLASH;}
    {FNAME}                                         {yybegin(BEFORE_SLASH); return YappingTypes.FNAME;}
    {FNAME_ENDARROW}                                {
          yypushback(2);
          yybegin(BEFORE_SLASH);
          return YappingTypes.FNAME;
      }
    // however we do require a name at the end, a dangling / is invalid (and can cause confusion)
    // e.g.        @my/folder/
    //             123  # resolves to my/folder/123
    //             @my/folder/  # resolves to my/folder
}
<AFTER_JSCOPE> {
    // identifier.. is not valid
    //{SCOPE}                                         {return TokenType.BAD_CHARACTER;}
    // after jscope first char should be a jletter (or regex/literal)
    [:jletter:][:jletterdigit:]*                    {yybegin(BEFORE_JSCOPE); return YappingTypes.JNAME;}
    // everything else is treated as a bad character, cannot have a java path that ends in '.'
}
<AFTER_YSCOPE> {
    {YNAME}                                         {yybegin(BEFORE_YSCOPE); return YappingTypes.YNAME;}
    {NATURAL}                                       {yybegin(BEFORE_YSCOPE); return YappingTypes.NATURAL;}
}
<PENDING_SLASH> {
    // before a path separator, comments and whitespaces separate to next item
    {SLASH}                                         {yybegin(AFTER_SLASH); return YappingTypes.SLASH; }
    // other valid tokens are covered in DEFAULT 1
}
<PENDING_JSCOPE> {
    {DOT}                                           {yybegin(AFTER_JSCOPE); return YappingTypes.DOT; }
}
<PENDING_YSCOPE> {
    {DOT}                                           {yybegin(AFTER_YSCOPE); return YappingTypes.DOT; }
}
<LITERAL> {
    // escaped quote in string
    \\\"                                            {}
    // end of quote
    {LITERAL_QUOTE}                                 {
                                                        yybegin(getCurrentFallback());
                                                        return YappingTypes.LITERAL;
                                                    }
    // body: everything except " and \
    [^\"\\]+                                        {}
    // body: everything \. except \"
    \\[^\"]                                         {}
    <<EOF>>                                         { reset(); return TokenType.ERROR_ELEMENT; }
}
<REGEX> {
    // escaped
    \\\`                                            { yybegin(REGEX); }
    \\\[                                            { yybegin(REGEX); }
    \[                                              { yybegin(REGEX_CLASS); }
    // end of regex, transition back to INITIAL
    {REGEX_QUOTE}                                   { yybegin(getCurrentFallback()); return YappingTypes.REGEX; }
    // body: everything except `, \, and [
    [^\\\`\[]+                                      {}
    // body: everything \. except \` and \[
    \\[^\`\[]                                       {}
    <<EOF>>                                         { reset(); return TokenType.ERROR_ELEMENT; }
}
<REGEX_CLASS> {
    // escaped end of char class
    \\\]                                            {}
    // end of char class, transition back to REGEX
    \]                                              { yybegin(REGEX); }
    // body: everything except ] and \
    [^\\\]]+                                        {}
    // body: everything \. except \]
    \\[^\]]                                         {}
    <<EOF>>                                         { reset(); return TokenType.ERROR_ELEMENT; }
}

[^]                                                 { return TokenType.BAD_CHARACTER; }

