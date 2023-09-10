package io.hostilerobot.protag.parser;

import io.hostilerobot.protag.lang.Token;

public interface Parser<T extends Token, N, S extends TokenSource<T>> {
    public void reInit(S tokenManager);
    public S getTokenSource();
    public T getToken(int idx);
    public N parse() throws ParseException;
}
