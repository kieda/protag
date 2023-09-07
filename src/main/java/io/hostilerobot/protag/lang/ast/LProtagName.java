package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class LProtagName extends AbstractProtagNode implements CStringNode, TProtagPathSegmentItem, TProtagPathItem {
    private final String name;
    public LProtagName(String name) {
        this.name = name;
    }

    @Override
    public String getRawValue() {
        return name;
    }
}
