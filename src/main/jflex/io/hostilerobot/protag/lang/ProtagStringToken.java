package io.hostilerobot.protag.lang;

import io.hostilerobot.protag.meta.Location;

public final class ProtagStringToken extends ProtagSymbol{
    public ProtagStringToken(ProtagTokenType type, Location start, CharSequence symbol) {
        super(type.getTerminalId(), type, start, start.calculateEndLocation(symbol), symbol);

        if(type.isFixed())
            throw new IllegalArgumentException("cannot use fixed type %s".formatted(type));
    }

    @Override
    public CharSequence getSymbol() {
        return (CharSequence) super.value;
    }
}
