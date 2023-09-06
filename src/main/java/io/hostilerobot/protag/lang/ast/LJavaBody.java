package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class LJavaBody extends AbstractProtagNode implements CStringNode, TJavaPathSegmentItem {
    private final String jbody;
    public LJavaBody(ASTMetaInfo meta, String jbody) {
        super(meta);
        this.jbody = jbody;
    }
    @Override
    public String getRawValue() {
        return jbody;
    }
}
