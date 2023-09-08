package io.hostilerobot.protag.lang;

public interface TokenSource<T extends Token> extends TokenManager {
    @Override
    public T getNextToken();

}
