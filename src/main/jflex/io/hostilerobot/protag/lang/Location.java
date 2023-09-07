package io.hostilerobot.protag.lang;

public record Location(int line, int column, int offset) {
    public static Location UNKNOWN = new Location(-1, -1, -1);

    public boolean isUnknown() {
        return line == -1 || column == -1 || offset == -1;
    }

    public Location calculateEndLocation(CharSequence input) {
        int length = input.length();
        int endOffset = offset + length;
        int endLine = line;
        int endColumn = column + length;
        // note -- yylength() returns the number of chars (and not code points) till the end of the match
        boolean hasCarriage = false;
        for (int i = 0; i < length; i++) {
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
        if (hasCarriage) {
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
