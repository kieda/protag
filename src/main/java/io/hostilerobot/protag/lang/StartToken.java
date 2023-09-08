package io.hostilerobot.protag.lang;

public final class StartToken extends ProtagSymbol{
    public StartToken() {
        super(ProtagTokenType.$START);
    }

    @Override
    public CharSequence getSymbol() {
        return ProtagTokenType.$START.name();
    }
}
