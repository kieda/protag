package io.hostilerobot.protag.lang;

import io.hostilerobot.protag.meta.Location;

public final class EOFToken extends ProtagSymbol{
    public EOFToken(Location start) {
        super(ProtagTokenType.EOF.getTerminalId(), ProtagTokenType.EOF, start, start, null);
    }
    public EOFToken(ProtagSymbol start, ProtagSymbol end, Object val) {
        super(ProtagTokenType.EOF.getTerminalId(), ProtagTokenType.EOF, start.getStart(), end.getEnd(), val);
    }

    @Override
    public CharSequence getSymbol() {
        return "<EOF>";
    }
}