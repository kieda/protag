package io.hostilerobot.protag.lang;

public final class StartToken extends ProtagSymbol{
    public String startName;
    public StartToken(int id, String name, int state) {
        super(id, ProtagTokenType.$START, state);
        this.startName = name;
    }

    @Override
    public CharSequence getSymbol() {
        return startName;
    }
}
