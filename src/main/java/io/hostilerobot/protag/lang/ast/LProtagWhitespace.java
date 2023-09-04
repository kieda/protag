package io.hostilerobot.protag.lang.ast;

public record LProtagWhitespace(ProtagNode parent, CharSequence space) implements ProtagNode{
    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
