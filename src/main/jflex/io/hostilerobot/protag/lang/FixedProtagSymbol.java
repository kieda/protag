package io.hostilerobot.protag.lang;

public class FixedProtagSymbol extends ProtagSymbol {
    public FixedProtagSymbol(ProtagSymbolType type, Location start) {
        super(type, start);
        if(!type.isFixed())
            throw new IllegalArgumentException("cannot use non-fixed type %s".formatted(type));
    }

    @Override
    public CharSequence getSymbol() {
        return getType().getTokenSymbol();
    }
}
