package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class LJavaBody extends AbstractProtagNode implements CStringNode, TJavaPathSegmentItem {
    private final String jbody;
    public LJavaBody(String jbody) {
        this.jbody = jbody;
    }
    @Override
    public String getRawValue() {
        return jbody;
    }

}
