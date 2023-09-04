package io.hostilerobot.protag.lang;

import java_cup.runtime.Symbol;

public sealed abstract class ProtagSymbol extends Symbol permits ProtagSpecialToken, ProtagNonTerminalSymbol, EOFToken, StartToken, ProtagStringToken, ProtagNaturalToken {
    // location (start), string length, location (end)
    // String value (?)
    // ProtagTokenType tokenType
    private final ProtagTokenType type;
    private final Location start;
    private final Location end;

//  *** Constructor: Special Symbols (START) ***
    protected ProtagSymbol(int id, ProtagTokenType type, int state) {
        super(id);
        super.parse_state = state;
        this.type = type;
        this.start = null;
        this.end = null;
    }
//  *** Constructor: All other Symbols (DOT, Properties, LITERAL) ***
    public ProtagSymbol(int id, ProtagTokenType type, Location start, Location end, Object val) {
        super(id, start.offset, end.offset, val);
        this.type =  type;
        this.start = start;
        this.end = end;
    }

    public Location getStart() {return start; }
    public Location getEnd() {
        return end;
    }

    public abstract CharSequence getSymbol();
    public ProtagTokenType getType() {
        return type;
    }

    public record Location(int line, int column, int offset) {
        public Location calculateEndLocation(CharSequence input) {
            int length = input.length();
            int endOffset = offset + length;
            int endLine = line;
            int endColumn = column + length;
            // note -- yylength() returns the number of chars (and not code points) till the end of the match
            boolean hasCarriage = false;
            for(int i = 0; i < length; i++) {
                switch (input.charAt(i)) {
                    case '\n' -> {
                        if (hasCarriage) hasCarriage = false;
                        endLine++;
                        endColumn = (length - i) + 1;
                    }
                    case '\r' -> hasCarriage = true; // \r\n counts as a single new line
                    default -> {
                        if (hasCarriage) {
                            endLine++;
                            endColumn = (length - i);
                            hasCarriage = false;
                        }
                    }
                }
            }
            // string ends in \r
            if(hasCarriage) {
                endLine++;
                endColumn = 0;
            }

            return new Location(endLine, endColumn, endOffset);
        }

        @Override
        public String toString() {
            return "%d:%d".formatted(line, column);
        }
    }

    @Override
    public String toString() {
        String first;
        if(type != null) {
            first = "%s#%d(%s)".formatted(type, super.sym, getSymbol());
        } else {
            first = "#%d".formatted(super.sym);
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