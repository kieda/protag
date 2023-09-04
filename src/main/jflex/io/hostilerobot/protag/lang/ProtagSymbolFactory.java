package io.hostilerobot.protag.lang;

import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProtagSymbolFactory implements SymbolFactory {
    private static Map<String, ProtagTokenType> toEnum = EnumSet.allOf(ProtagTokenType.class).stream()
            .collect(Collectors.toUnmodifiableMap(ProtagTokenType::name, v -> v));

    public static ProtagTokenType getTokenType(String name) {
        ProtagTokenType type = toEnum.get(name);
        if(type == null) {
            System.out.printf("NOTE: %s is null\n", name);
        }
        return type;
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object val) {
        ProtagTokenType tokenType = getTokenType(name);
        return new ProtagNonTerminalSymbol(id, tokenType, (ProtagSymbol) left, (ProtagSymbol) right, val);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        return newSymbol(name, id, left, right, null);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol center, Object val) {
        ProtagTokenType tokenType = getTokenType(name);
        return new ProtagNonTerminalSymbol(id, tokenType, (ProtagSymbol) center, val);
    }

    @Override
    public Symbol newSymbol(String name, int id, Object val) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Symbol newSymbol(String name, int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Symbol startSymbol(String name, int id, int state) {
        return new StartToken(id, name, state);
    }
}
