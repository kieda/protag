package io.hostilerobot.protag.ast;

public final class LProtagLiteral extends AbstractProtagNode implements CStringNode, TCommonPathSegmentItem, TCommonPathItem {
    private final String literal;
    public LProtagLiteral(String literal) {
        this.literal = literal;
    }
    @Override
    public String getRawValue() {
        return literal;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(getClass().getSimpleName(), literal);
    }
}
