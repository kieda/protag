package io.hostilerobot.protag.lang.ast;

public record LJavaName(ProtagNode parent, String jbody) implements CStringNode, TJavaPathSegmentItem, TJavaPathItem {
    @Override
    public String getRawValue() {
        return jbody;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
