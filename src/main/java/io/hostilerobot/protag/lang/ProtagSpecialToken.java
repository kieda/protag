package io.hostilerobot.protag.lang;

public final class ProtagSpecialToken extends ProtagSymbol {
    public ProtagSpecialToken(ProtagTokenType type, Location start) {
        super(type.getTerminalId(), type, start, start.calculateEndLocation(type.getTokenSymbol()));
        if(!type.isFixed())
            throw new IllegalArgumentException("cannot use non-fixed type %s".formatted(type));
    }

    @Override
    public CharSequence getSymbol() {
        return getType().getTokenSymbol();
    }
}
