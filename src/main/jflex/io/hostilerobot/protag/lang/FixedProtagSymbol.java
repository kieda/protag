package io.hostilerobot.protag.lang;

public class FixedProtagSymbol extends ProtagSymbol {
    public FixedProtagSymbol(int id, ProtagTokenType type) {
        super(id, type, null);
    }
    public FixedProtagSymbol(int id, ProtagTokenType type, Location start) {
        super(id, type, start);
        if(!type.isFixed())
            throw new IllegalArgumentException("cannot use non-fixed type %s".formatted(type));
    }

    @Override
    public CharSequence getSymbol() {
        return getType().getTokenSymbol();
    }
}
