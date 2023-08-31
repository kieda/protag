package io.hostilerobot.protag.lang.ast;

public record LJavaBody(ProtagNode parent, String jbody) implements CStringNode, TJavaPathSegmentItem {
    @Override
    public String getRawValue() {
        return jbody;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
