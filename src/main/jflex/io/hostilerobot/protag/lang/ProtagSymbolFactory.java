package io.hostilerobot.protag.lang;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

public class ProtagSymbolFactory implements SymbolFactory {
    public static ProtagTokenType getTokenType(String name) {
        return ProtagTokenType.valueOf(name);
    }
    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object val) {
        return null;
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol left, Symbol right) {
        return null;
    }

    @Override
    public Symbol newSymbol(String name, int id, Symbol center, Object val) {
        return null;
    }

    @Override
    public Symbol newSymbol(String name, int id, Object val) {
        return null;
    }

    @Override
    public Symbol newSymbol(String name, int id) {
        ProtagTokenType type = getTokenType(name);
        if(type.isFixed()) {
            return new FixedProtagSymbol(name, id);
        }
        return null;
    }

    @Override
    public Symbol startSymbol(String name, int id, int state) {
        // zzz constructor with int state is package private for some reason
        return new ComplexSymbolFactory.ComplexSymbol(name, id, state);
    }
}
