package io.hostilerobot.protag.ast;

public final class LFileName extends AbstractProtagNode implements CStringNode, TFilePathSegmentItem, TFilePathItem {
    private final String fname;
    public LFileName(String fname) {
        this.fname = fname;
    }
    @Override
    public String getRawValue() {
        return fname;
    }

    @Override
    public String toString() {
        return "LFileName[%s]".formatted(fname);
    }
}
