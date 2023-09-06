package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class LProtagBody extends AbstractProtagNode implements CStringNode, TProtagPathSegmentItem {
    private final String body;
    public LProtagBody(ASTMetaInfo meta, String body) {
        super(meta);
        this.body = body;
    }
    @Override
    public String getRawValue() {
        return body;
    }
}
