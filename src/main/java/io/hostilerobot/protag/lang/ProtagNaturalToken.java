package io.hostilerobot.protag.lang;

public final class ProtagNaturalToken extends ProtagSymbol{
    private final CharSequence naturalString;
    private final int nat;
    private static int parseNatural(CharSequence val) {
        if(val.length() > 0 && val.charAt(0) == '+') {
            throw new NumberFormatException(String.format("Illegal leading plus sign on natural string %s.", val));
        }
        return Integer.parseUnsignedInt(String.valueOf(val));
    }

    public ProtagNaturalToken(Location start, CharSequence val) {
        super(ProtagTokenType.NATURAL.getTerminalId(), ProtagTokenType.NATURAL, start, start.calculateEndLocation(val));
        this.naturalString = val;
        this.nat = parseNatural(val);
    }

    @Override
    public Integer getValue() {
        return nat;
    }

    @Override
    public CharSequence getSymbol() {
        return naturalString;
    }
}
