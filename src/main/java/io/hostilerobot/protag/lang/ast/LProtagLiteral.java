package io.hostilerobot.protag.lang.ast;

public final class LProtagLiteral extends AbstractProtagNode implements CStringNode, TCommonPathSegmentItem, TCommonPathItem {
    private final String literal;
    public LProtagLiteral(String literal) {
        this.literal = literal;
    }
    @Override
    public String getRawValue() {
        return literal;
    }
}
