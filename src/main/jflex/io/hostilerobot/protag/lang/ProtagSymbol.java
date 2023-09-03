package io.hostilerobot.protag.lang;

import java_cup.runtime.Symbol;

public abstract class ProtagSymbol extends Symbol {
    // location (start), string length, location (end)
    // String value (?)
    // ProtagTokenType tokenType
    private final ProtagTokenType type;
    private final Location start;
    private final Location end;

    public ProtagSymbol(int id, ProtagTokenType type) {
        super(id);
        this.type = type;
        start = null;
        end = null;
    }
    public ProtagSymbol(int id, ProtagTokenType type, Object val) {
        super(id, val);
        this.type = type;
        start = null;
        end = null;
    }
    public ProtagSymbol(int id, ProtagTokenType type, Symbol center, Object val) {
        super(id, center, val);
        this.type = type;
        start = null;
        end = null;
    }
    public ProtagSymbol(int id, ProtagTokenType type, Symbol left, Symbol right) {
        super(id, left, right);
        this.type = type;
        start = null;
        end = null;
    }
    public ProtagSymbol(int id, ProtagTokenType type, Symbol left, Symbol right, Object val) {
        super(id, left, right, val);
        this.type = type;
        start = null;
        end = null;
    }
    public ProtagSymbol(int id, ProtagTokenType type, Location start) {
        this(id, type, start, start.calculateEndLocation(type.getTokenSymbol()), type.getTokenSymbol());
    }
    public ProtagSymbol(int id, ProtagTokenType type, Location start, CharSequence symbol) {
        this(id, type, start, start.calculateEndLocation(symbol), symbol);
    }
    private ProtagSymbol(int id, ProtagTokenType type, Location start, Location end, CharSequence symbol) {
        super(id, start.offset, end.offset, symbol);
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
    }
}


// implementations: fixed symbol
//