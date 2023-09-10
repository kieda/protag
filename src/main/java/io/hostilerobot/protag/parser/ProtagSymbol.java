package io.hostilerobot.protag.parser;

import io.hostilerobot.protag.parser.Token;

public abstract sealed class ProtagSymbol extends Token permits
        ProtagStringToken, ProtagNaturalToken, /* variable types of tokens, strings and nat numbers */
        ProtagSpecialToken, /* operator types of tokens, contents will remain the same */
        StartToken, EOFToken /* EOF token. Start is represented by the superclass Token() */{
    // location (start), string length, location (end)
    // String value (?)
    // ProtagTokenType tokenType
    private final ProtagTokenType type;
    private final Location start;
    private final Location end;

    protected ProtagSymbol(ProtagTokenType type) {
        super();
        this.type = type;
        start = null;
        end = null;
    }

//  *** Constructor: All other Symbols (DOT, Properties, LITERAL) ***
    public ProtagSymbol(int id, ProtagTokenType type, Location start, Location end) {
        super(id, type.getImage());
        this.type =  type;
        this.start = start;
        this.end = end;
        super.beginLine = start.line();
        super.beginColumn = start.column();
        super.endLine = end.line();
        super.endColumn = end.column();
    }

    public Location getStart() {return start; }
    public Location getEnd() {
        return end;
    }

    public abstract CharSequence getSymbol();
    public ProtagTokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        String first;
        if(type != null) {
            first = "%s#%d(%s)".formatted(type, super.kind, getSymbol());
        } else {
            first = "#%d".formatted(super.kind);
        }

        if(start != null) {
            if(end == null || start.equals(end)) {
                return first + "[%s]".formatted(start);
            } else {
                return first + "[%s, %s]".formatted(start, end);
            }
        }
        return first;
    }
}


// implementations: fixed symbol
//