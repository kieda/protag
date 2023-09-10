package io.hostilerobot.protag.ast;

public final class LProtagBody extends AbstractProtagNode implements CStringNode, TProtagPathSegmentItem {
    private final String body;
    public LProtagBody(String body) {
        this.body = body;
    }
    @Override
    public String getRawValue() {
        return body;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(getClass().getSimpleName(), body);
    }
}
