package io.hostilerobot.protag.lang;

public abstract class ProtagSymbol {
    // location (start), string length, location (end)
    // String value (?)
    // ProtagTokenType tokenType
    private final ProtagSymbolType type;
    private final Location start;
    private Location end = null; // lazy evaluate
    public ProtagSymbol(ProtagSymbolType type, Location start) {
        this.type = type;
        this.start = start;
    }

    public Location getStart() {
        return start;
    }
    public Location getEnd() {
        if(end == null) {
            CharSequence symbol = getSymbol();
            end = start.calculateEndLocation(symbol, symbol.length());
        }
        return end;
    }

    public abstract CharSequence getSymbol();
    public ProtagSymbolType getType() {
        return type;
    }

    public record Location(int line, int column, int offset) {
        public Location calculateEndLocation(CharSequence input, int length) {
            if(input.length() < length) {
                throw new IllegalArgumentException("Getting location at %d, past %s maximum length".formatted(length, input));
            }
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