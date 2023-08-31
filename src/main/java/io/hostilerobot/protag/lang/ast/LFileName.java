package io.hostilerobot.protag.lang.ast;

public record LFileName(ProtagNode parent, String fname) implements CStringNode, TFilePathSegmentItem, TFilePathItem {
    @Override
    public String getRawValue() {
        return fname;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
