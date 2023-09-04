package io.hostilerobot.protag.lang.ast;

public record LProtagName(ProtagNode parent, String name) implements CStringNode, TProtagPathSegmentItem, TProtagPathItem {
    @Override
    public ProtagNode getParent() {
        return parent;
    }

    @Override
    public String getRawValue() {
        return name;
    }
}
