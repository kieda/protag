package io.hostilerobot.protag.ast;

public final class LProtagName extends AbstractProtagNode implements CStringNode, TProtagPathSegmentItem, TProtagPathItem {
    private final String name;
    public LProtagName(String name) {
        this.name = name;
    }

    @Override
    public String getRawValue() {
        return name;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(getClass().getSimpleName(), name);
    }
}
