package io.hostilerobot.protag.lang;

public class StringProtagSymbol extends ProtagSymbol{
    private final String symbol;
    public StringProtagSymbol(ProtagSymbolType type, String symbol, Location start) {
        super(type, start);
        this.symbol = symbol;
        if(type.isFixed())
            throw new IllegalArgumentException("cannot use fixed type %s".formatted(type));
    }

    @Override
    public CharSequence getSymbol() {
        return symbol;
    }
}
