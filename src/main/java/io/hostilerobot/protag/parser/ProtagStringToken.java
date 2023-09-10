package io.hostilerobot.protag.parser;

public final class ProtagStringToken extends ProtagSymbol{
    private final CharSequence symbol;
    public ProtagStringToken(ProtagTokenType type, Location start, CharSequence symbol) {
        super(type.getTerminalId(), type, start, start.calculateEndLocation(symbol));

        if(type.isFixed())
            throw new IllegalArgumentException("cannot use fixed type %s".formatted(type));
        this.symbol = symbol;
    }

    @Override
    public CharSequence getValue() {
        return symbol;
    }

    @Override
    public CharSequence getSymbol() {
        return symbol;
    }
}
