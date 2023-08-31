package io.hostilerobot.protag.lang.ast;

public record LProtagLiteral(ProtagNode parent, String literal) implements CStringNode, TCommonPathSegmentItem, TCommonPathItem {
    @Override
    public String getRawValue() {
        return literal;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
