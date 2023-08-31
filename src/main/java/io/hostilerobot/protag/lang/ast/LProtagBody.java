package io.hostilerobot.protag.lang.ast;

public record LProtagBody(ProtagNode parent, String body) implements CStringNode, TProtagPathSegmentItem {
    @Override
    public String getRawValue() {
        return body;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
