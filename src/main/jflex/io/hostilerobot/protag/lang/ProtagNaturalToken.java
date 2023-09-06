package io.hostilerobot.protag.lang;

import io.hostilerobot.protag.meta.Location;

public final class ProtagNaturalToken extends ProtagSymbol{
    private final CharSequence naturalString;
    private static int parseNatural(CharSequence val) {
        if(val.length() > 0 && val.charAt(0) == '+') {
            throw new NumberFormatException(String.format("Illegal leading plus sign on natural string %s.", val));
        }
        return Integer.parseUnsignedInt(String.valueOf(val));
    }

    public ProtagNaturalToken(Location start, CharSequence val) {
        super(ProtagTokenType.NATURAL.getTerminalId(), ProtagTokenType.NATURAL, start, start.calculateEndLocation(val), parseNatural(val));
        this.naturalString = val;
    }

    @Override
    public CharSequence getSymbol() {
        return naturalString;
    }
}
