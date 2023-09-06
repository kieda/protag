package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class LProtagLiteral extends AbstractProtagNode implements CStringNode, TCommonPathSegmentItem, TCommonPathItem {
    private final String literal;
    public LProtagLiteral(ASTMetaInfo meta, String literal) {
        super(meta);
        this.literal = literal;
    }
    @Override
    public String getRawValue() {
        return literal;
    }
}
