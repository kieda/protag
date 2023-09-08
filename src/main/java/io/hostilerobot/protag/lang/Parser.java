package io.hostilerobot.protag.lang;

public interface Parser<T extends Token, N, S extends TokenSource<T>> {
    public void reInit(S tokenManager);
    public S getTokenSource();
    public T getToken(int idx);
    public N parse() throws ParseException;
}
