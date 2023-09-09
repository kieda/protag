package io.hostilerobot.protag.lang.ast;

public final class LJavaBody extends AbstractProtagNode implements CStringNode, TJavaPathSegmentItem {
    private final String jbody;
    public LJavaBody(String jbody) {
        this.jbody = jbody;
    }
    @Override
    public String getRawValue() {
        return jbody;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(getClass().getSimpleName(), jbody);
    }
}
