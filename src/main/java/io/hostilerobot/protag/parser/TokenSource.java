package io.hostilerobot.protag.parser;

import io.hostilerobot.protag.parser.Token;
import io.hostilerobot.protag.parser.TokenManager;

public interface TokenSource<T extends Token> extends TokenManager {
    @Override
    public T getNextToken();

}
