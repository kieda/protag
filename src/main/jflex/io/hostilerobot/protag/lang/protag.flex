package io.hostilerobot.protag.lang;

import java.util.ArrayDeque;import java.util.Deque;
import static io.hostilerobot.protag.lang.ProtagTokenType.*;

%%
%public
%class ProtagLexer
%unicode
%cupsym ProtagSymbols
//%extends ProtagSymbols
%type ProtagSymbol
%cup
%char
%line
%column
%{
// StringBuilder current = new StringBuilder();

private ProtagSymbol.Location currentLocation() {
    return new ProtagSymbol.Location(yyline, yycolumn, (int)yychar);
}

// ProtagTokenType x Location -> Symbol
// ProtagTokenType x String x Location
private ProtagSymbol symbol( ProtagTokenType type ) {
    if(type.isFixed()) {
        return new FixedProtagSymbol(type, currentLocation());
    } else {
        return new StringProtagSymbol(type, yytext(), currentLocation());
    }
}

enum PathContext{
      // base is used when we're not actively in a yapping segment.
      BASE(YYINITIAL, YYINITIAL),
      FILE(AFTER_SLASH, BEFORE_SLASH),
      JAVA(AFTER_JSCOPE, BEFORE_JSCOPE),
      PROTAG(AFTER_PSCOPE, BEFORE_PSCOPE);
      private final int fallback;
      private final int initialState;
      PathContext(int initialState, int fallback) {
          this.initialState = initialState;
          this.fallback = fallback;
      }
}
// we want to handle java paths !com.user.Plugin
// different from file paths @file/user/pugin.yap
// different from PROTAG paths { ... }.my-value.3.length
private Deque<PathContext> context = new ArrayDeque<>();
{
  // top level is all BASE
  // this item should be here throughout all lexing
  context.push(PathContext.BASE);
}

public void reset() {
    context.clear();
    context.push(PathContext.BASE);
    yybegin(YYINITIAL);
}

private String getStateName(int state) {
    switch(state) {
        case AFTER_JSCOPE: return "AFTER_JSCOPE";
        case AFTER_SLASH: return "AFTER_SLASH";
        case AFTER_PSCOPE: return "AFTER_PSCOPE";
        case BEFORE_JSCOPE: return "BEFORE_JSCOPE";
        case BEFORE_SLASH: return "BEFORE_SLASH";
        case BEFORE_PSCOPE: return "BEFORE_PSCOPE";
        case PENDING_JSCOPE: return "PENDING_JSCOPE";
        case PENDING_SLASH: return "PENDING_SLASH";
        case PENDING_PSCOPE: return "PENDING_PSCOPE";
        case LITERAL_STATE: return "LITERAL";
        case REGEX_STATE: return "REGEX";
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
    context.push(PathContext.PROTAG);
    yybegin(REGEX_STATE);
    debugCurrentState("END encounterRegexSegment");
}
// new path that starts with a literal
// !asdf "asdf"
//       ^
private void encounterLiteralSegment() {
    debugCurrentState("START encounterLiteralSegment");
    endTransition();
    context.push(PathContext.PROTAG);
    yybegin(LITERAL_STATE);
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
private void encounterProTagPath() {
    debugCurrentState("START encounterProTagPath");
    endTransition();
    context.push(PathContext.PROTAG);
    yybegin(PathContext.PROTAG.fallback);
    debugCurrentState("END encounterProTagPath");
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
        yybegin(PathContext.PROTAG.fallback);
    }
    // todo - should we throw if we encounter mismatched segments?
    //        ex: asdf)asdf
    //        currently we just fall back to PROTAG and let the parser deal with it
    debugCurrentState("END closeCurrentSegment");
}

private static String zzToPrintable(CharSequence cs) {
    return zzToPrintable(cs.toString());
}
%}

%eofval{
    return symbol(EOF);
%eofval}
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
PBODYCHAR      = //[[{FNAMECHAR}] && [^\&\+\'\$\|\~\*\^\%\\\?\%\.]]
    [^\/\"\`\!\@\,\r\n\t\f\=\-\:\;\ \#\[\]\(\)\{\}\<\>\&\+\'\$\|\~\*\^\%\\\?\%\.]
PFIRSTCHAR     = //[{YFIRSTCHAR} && [^0-9]]
    [^\/\"\`\!\@\,\r\n\t\f\=\-\:\;\ \#\[\]\(\)\{\}\<\>\&\+\'\$\|\~\*\^\%\\\?\%\.0-9]
//YFIRSTCHAR     = [^\/\"\'\`\!\@\r\n\t\f\ \#\[\]\(\)\{\}\<\>\:\&\+\;\|\=\,\~\*\^\%\\\?\%\.[0-9]]
//PBODYCHAR      = [^\/\"\'\`\!\@\r\n\t\f\ \#\[\]\(\)\{\}\<\>\:\&\-\+\;\|\=\,\~\*\^\%\\\?\%\.]
PBODY          = ({PBODYCHAR}|(\-{PFIRSTCHAR}))+    // asdf10hij maker-space10 10-asdf
PNAME          = {PFIRSTCHAR}(({PBODYCHAR}|(\-{PFIRSTCHAR}))*) // sadfasdf asdf10 x10-asdf
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
%state LITERAL_STATE
%state REGEX_STATE
%state REGEX_CLASS
%state BEFORE_SLASH, AFTER_SLASH, PENDING_SLASH
%state BEFORE_JSCOPE, AFTER_JSCOPE, PENDING_JSCOPE
%state BEFORE_PSCOPE, AFTER_PSCOPE, PENDING_PSCOPE

%%

// we have found a new identifier.

// DEFAULT 1: new identifier stops previous path
<PENDING_SLASH, PENDING_JSCOPE, PENDING_PSCOPE> {
    {LITERAL_QUOTE}                                 {
                                                        // we are encountering a new literal
                                                        encounterLiteralSegment();
                                                    }
    {REGEX_QUOTE}                                   {
                                                        encounterRegexSegment();
                                                    }
    {PNAME}                                         {
                                                        encounterProTagPath();
                                                        return symbol(PNAME);
                                                    }
    {NATURAL}                                       {
                                                        encounterProTagPath();
                                                        return symbol(NATURAL);
                                                    }
    {MAP_START}                                     {
                                                        encounterOpenSegment();
                                                        return symbol(MAP_START);
                                                    }
    {MAP_END}                                       {
                                                        closeCurrentSegment();
                                                        return symbol(MAP_END);
                                                    }
    {LIST_START}                                    {
                                                        encounterOpenSegment();
                                                        return symbol(LIST_START);
                                                    }
    {LIST_END}                                      {
                                                        closeCurrentSegment();
                                                        return symbol(LIST_END);
                                                    }
    {PRECEDENCE_START}                              {
                                                        encounterOpenSegment();
                                                        return symbol(PRECEDENCE_START);
                                                    }
    {PRECEDENCE_END}                                {
                                                        closeCurrentSegment();
                                                        return symbol(PRECEDENCE_END);
                                                    }
}

// DEFAULT 2: new java|file path stops previous path
//            and binary operators: items that may end the current path
<YYINITIAL, PENDING_SLASH, PENDING_JSCOPE, PENDING_PSCOPE, BEFORE_SLASH, BEFORE_JSCOPE, BEFORE_PSCOPE> {
    {FPATH_START}                                   {
                                                        /* fall back to BEFORE_SLASH when we're done parsing regex or literal */
                                                        encounterFilePath();
                                                        return symbol(FPATH_START);
                                                    }
    {JPATH_START}                                   {
                                                        encounterJavaPath();
                                                        return symbol(JPATH_START);
                                                    }
    {TRANSITION_SEP_L}                              {
                                                        endCurrentPath();
                                                        return symbol(TRANSITION_SEP_L);
                                                    }
    {TRANSITION_SEP_R}                              {
                                                        endCurrentPath();
                                                        return symbol(TRANSITION_SEP_R);
                                                    }
    {PAIR_SEP}                                      {
                                                        endCurrentPath();
                                                        return symbol(PAIR_SEP);
                                                    }
    {LIST_SEP}                                      {
                                                        endCurrentPath();
                                                        return symbol(LIST_SEP);
                                                    }
    {PROPERTY_SEP}                                  {
                                                        endCurrentPath();
                                                        return symbol(PROPERTY_SEP);
                                                    }
}
// DEFAULT 3: whitespace, comments, etc in yapping context
<YYINITIAL> {
    {COMMENT}                                       { return symbol(COMMENT); }
    {WHITESPACE}                                    { return symbol(WHITESPACE); }
    // parts in yapping paths
    {PNAME}                                         {
                                                        encounterProTagPath();
                                                        return symbol(PNAME);
                                                    }
    {NATURAL}                                       {
                                                        encounterProTagPath();
                                                        return symbol(NATURAL);
                                                    }

    {REGEX_QUOTE}                                   {
                                                        encounterRegexSegment();
                                                    }

    {LITERAL_QUOTE}                                 {
                                                        encounterLiteralSegment();
                                                    }

    {DOT}                                           { return symbol(DOT); }
    {SLASH}                                         { return symbol(SLASH); }
}
// DEFAULT 4: items in a path that can be followed by . or /
<YYINITIAL, BEFORE_SLASH, BEFORE_JSCOPE, BEFORE_PSCOPE, AFTER_JSCOPE, AFTER_SLASH, AFTER_PSCOPE> {
    {MAP_START}                                     {
                                                        openCurrentSegment();
                                                        return symbol(MAP_START);
                                                    }
    {MAP_END}                                       {
                                                        closeCurrentSegment();
                                                        return symbol(MAP_END);
                                                    }
    {LIST_START}                                    {
                                                        openCurrentSegment();
                                                        return symbol(LIST_START);
                                                    }
    {LIST_END}                                      {
                                                        closeCurrentSegment();
                                                        return symbol(LIST_END);
                                                    }
    {PRECEDENCE_START}                              {
                                                        openCurrentSegment();
                                                        return symbol(PRECEDENCE_START);
                                                    }
    {PRECEDENCE_END}                                {
                                                        closeCurrentSegment();
                                                        return symbol(PRECEDENCE_END);
                                                    }
}
<BEFORE_SLASH, BEFORE_JSCOPE, BEFORE_PSCOPE, AFTER_JSCOPE, AFTER_SLASH, AFTER_PSCOPE> {
    // if have another token that can be used in the path, then use it
    // after REGEX or LITERAL are parsed. Will fall back to BEFORE_(SCOPE|SLASH)
    {REGEX_QUOTE}                                   {yybegin(REGEX_STATE);}
    {LITERAL_QUOTE}                                 {yybegin(LITERAL_STATE);}
}
// DEFAULT 5: items that may be in a file path but not in a java path/yapping path
<YYINITIAL, BEFORE_PSCOPE, BEFORE_JSCOPE, PENDING_PSCOPE, PENDING_JSCOPE, PENDING_SLASH> {
    {AND}                                           {
                                                        endCurrentPath();
                                                        return symbol(AND);
                                                    }
    {PLUS}                                          {
                                                        endCurrentPath();
                                                        return symbol(PLUS);
                                                    }
    {MINUS}                                         {
                                                        endCurrentPath();
                                                        return symbol(MINUS);
                                                    }
}
<BEFORE_PSCOPE, BEFORE_JSCOPE, PENDING_PSCOPE, PENDING_JSCOPE> {
    {SLASH}                                         {
                                                        endCurrentPath();
                                                        return symbol(SLASH);
                                                    }
}


<BEFORE_SLASH> {
    // encounter a file name, state doesn't change.
    {FNAME}                                         {return symbol(FNAME);}
    {FNAME_ENDARROW}                                {
          yypushback(2);
          return symbol(FNAME);
      }

    // encountered a slash - we may now have whitespace
    {SLASH}                                         {yybegin(AFTER_SLASH); return symbol(SLASH); }
    // pending slash - if we encounter another / we are still parsing file path. otherwise we are parsing a new item.
    {COMMENT}                                       {yybegin(PENDING_SLASH); return symbol(COMMENT);}
    {WHITESPACE}                                    {yybegin(PENDING_SLASH); return symbol(WHITESPACE);}
    // other valid tokens are covered in DEFAULT 2
}
<BEFORE_JSCOPE> {
    [:jletterdigit:]+                               {return symbol(JBODY);}
    // encountered a scope - we may now have whitespace
    {DOT}                                           {yybegin(AFTER_JSCOPE); return symbol(DOT); }
    // pending slash - if we encounter another / we are still parsing file path. otherwise we are parsing a new item.
    {COMMENT}                                       {yybegin(PENDING_JSCOPE); return symbol(COMMENT);}
    {WHITESPACE}                                    {yybegin(PENDING_JSCOPE); return symbol(WHITESPACE);}
    // other valid tokens are covered in DEFAULT 2
}
<BEFORE_PSCOPE> {
    {NATURAL}                                       {return symbol(NATURAL);}
    {PBODY}                                         {return symbol(PBODY);}

    {DOT}                                           {yybegin(AFTER_PSCOPE); return symbol(DOT); }

    {COMMENT}                                       {yybegin(PENDING_PSCOPE); return symbol(COMMENT); }
    {WHITESPACE}                                    {yybegin(PENDING_PSCOPE); return symbol(WHITESPACE); }
}
<AFTER_SLASH, AFTER_JSCOPE, AFTER_PSCOPE, PENDING_JSCOPE, PENDING_SLASH, PENDING_PSCOPE> {
    // after or pending a scope separator: comments and or whitespace will stay in the same state
    {WHITESPACE}                                    {return symbol(WHITESPACE);}
    {COMMENT}                                       {return symbol(COMMENT);}
}
<AFTER_SLASH> {
    // file//asdf is fine
    {SLASH}                                         {return symbol(SLASH);}
    {FNAME}                                         {yybegin(BEFORE_SLASH); return symbol(FNAME);}
    {FNAME_ENDARROW}                                {
          yypushback(2);
          yybegin(BEFORE_SLASH);
          return symbol(FNAME);
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
    [:jletter:][:jletterdigit:]*                    {yybegin(BEFORE_JSCOPE); return symbol(JNAME);}
    // everything else is treated as a bad character, cannot have a java path that ends in '.'
}
<AFTER_PSCOPE> {
    {PNAME}                                         {yybegin(BEFORE_PSCOPE); return symbol(PNAME);}
    {NATURAL}                                       {yybegin(BEFORE_PSCOPE); return symbol(NATURAL);}
}
<PENDING_SLASH> {
    // before a path separator, comments and whitespaces separate to next item
    {SLASH}                                         {yybegin(AFTER_SLASH); return symbol(SLASH); }
    // other valid tokens are covered in DEFAULT 1
}
<PENDING_JSCOPE> {
    {DOT}                                           {yybegin(AFTER_JSCOPE); return symbol(DOT); }
}
<PENDING_PSCOPE> {
    {DOT}                                           {yybegin(AFTER_PSCOPE); return symbol(DOT); }
}
<LITERAL_STATE> {
    // escaped quote in string
    \\\"                                            {}
    // end of quote
    {LITERAL_QUOTE}                                 {
                                                            yybegin(getCurrentFallback());
                                                            return symbol(LITERAL);
                                                        }
    // body: everything except " and \
    [^\"\\]+                                        {}
    // body: everything \. except \"
    \\[^\"]                                         {}
    <<EOF>>                                         { reset(); return symbol(ERROR_ELEMENT); }
}
<REGEX_STATE> {
    // escaped
    \\\`                                            { yybegin(REGEX_STATE); }
    \\\[                                            { yybegin(REGEX_STATE); }
    \[                                              { yybegin(REGEX_CLASS); }
    // end of regex, transition back to INITIAL
    {REGEX_QUOTE}                                   { yybegin(getCurrentFallback()); return symbol(REGEX); }
    // body: everything except `, \, and [
    [^\\\`\[]+                                      {}
    // body: everything \. except \` and \[
    \\[^\`\[]                                       {}
    <<EOF>>                                         { reset(); return symbol(ERROR_ELEMENT); }
}
<REGEX_CLASS> {
    // escaped end of char class
    \\\]                                            {}
    // end of char class, transition back to REGEX
    \]                                              { yybegin(REGEX_STATE); }
    // body: everything except ] and \
    [^\\\]]+                                        {}
    // body: everything \. except \]
    \\[^\]]                                         {}
    <<EOF>>                                         { reset(); return symbol(ERROR_ELEMENT); }
}

[^]                                                 { return symbol(BAD_CHARACTER); }

