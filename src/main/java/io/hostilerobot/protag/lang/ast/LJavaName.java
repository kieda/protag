package io.hostilerobot.protag.lang.ast;

public final class LJavaName extends AbstractProtagNode implements CStringNode, TJavaPathSegmentItem, TJavaPathItem {
    private final String jname;
    public LJavaName(String jname) {
        this.jname = jname;
    }
    @Override
    public String getRawValue() {
        return jname;
    }
}
