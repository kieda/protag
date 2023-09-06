package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class LJavaName extends AbstractProtagNode implements CStringNode, TJavaPathSegmentItem, TJavaPathItem {
    private final String jname;
    public LJavaName(ASTMetaInfo meta, String jname) {
        super(meta);
        this.jname = jname;
    }
    @Override
    public String getRawValue() {
        return jname;
    }
}
