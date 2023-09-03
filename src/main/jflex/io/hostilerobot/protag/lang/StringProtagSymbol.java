package io.hostilerobot.protag.lang;

public class StringProtagSymbol extends ProtagSymbol{
    public StringProtagSymbol(ProtagTokenType type, CharSequence symbol, Location start) {
        super(type, start, symbol);

        if(type.isFixed())
            throw new IllegalArgumentException("cannot use fixed type %s".formatted(type));
    }

    @Override
    public CharSequence getSymbol() {
        return (CharSequence) super.value;
    }
}
