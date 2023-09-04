package io.hostilerobot.protag.lang;

public non-sealed class ProtagNonTerminalSymbol extends ProtagSymbol{
    public ProtagNonTerminalSymbol(int id, ProtagTokenType type, ProtagSymbol left, ProtagSymbol right, Object val) {
        super(id, type, left.getStart(), right.getEnd(), val);
    }
    public ProtagNonTerminalSymbol(int id, ProtagTokenType type, ProtagSymbol middle, Object val) {
        super(id, type, middle.getStart(), middle.getEnd(), val);
    }

    @Override
    public CharSequence getSymbol() {
        return getType().name();
    }
}
