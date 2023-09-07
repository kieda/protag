package io.hostilerobot.protag.lang.ast;

public final class LProtagBody extends AbstractProtagNode implements CStringNode, TProtagPathSegmentItem {
    private final String body;
    public LProtagBody(String body) {
        this.body = body;
    }
    @Override
    public String getRawValue() {
        return body;
    }
}
