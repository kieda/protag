package io.hostilerobot.protag.parser;

import io.hostilerobot.protag.lang.Token;
import io.hostilerobot.protag.lang.TokenManager;

public interface TokenSource<T extends Token> extends TokenManager {
    @Override
    public T getNextToken();

}
