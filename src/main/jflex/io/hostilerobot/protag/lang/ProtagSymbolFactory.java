package io.hostilerobot.protag.lang;

import io.hostilerobot.protag.lang.ast.ProtagNode;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

import java.util.*;
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

    private Map<String, Map<ProtagNode, ProtagSymbol>> protagLookup
            = new HashMap<>();
    public ProtagSymbol lookup(String name, ProtagNode item) {
        var map = protagLookup.get(name);
        if(map != null) {
            return map.get(item);
        }
        return null;
    }
    private void put(String name, ProtagNode item, ProtagSymbol result) {
        protagLookup.computeIfAbsent(name, n -> new HashMap<>());
        protagLookup.get(name).put(item, result);
        System.out.printf("putting (%s, %s) -> %s\n", name, item, result);
    }
    private Symbol unpackAndPutEntry(String name, Object item, ProtagSymbol result) {
        if(item instanceof ProtagNode node) {
            put(name, node, result);
        }
        return result;
    }
    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object val) {
        ProtagTokenType tokenType = getTokenType(name);
        return unpackAndPutEntry(name, val, new ProtagNonTerminalSymbol(id, tokenType, (ProtagSymbol) left, (ProtagSymbol) right, val));
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        return newSymbol(name, id, left, right, null);
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol center, Object val) {
        ProtagTokenType tokenType = getTokenType(name);
        return unpackAndPutEntry(name, val, new ProtagNonTerminalSymbol(id, tokenType, (ProtagSymbol) center, val));
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
