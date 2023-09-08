package io.hostilerobot.protag.lang;

public final class EOFToken extends ProtagSymbol{
    public EOFToken(Location start) {
        super(ProtagTokenType.EOF.getTerminalId(), ProtagTokenType.EOF, start, start);
    }
    public EOFToken(ProtagSymbol start, ProtagSymbol end) {
        super(ProtagTokenType.EOF.getTerminalId(), ProtagTokenType.EOF, start.getStart(), end.getEnd());
    }

    @Override
    public CharSequence getSymbol() {
        return ProtagTokenType.EOF.getImage();
    }
}