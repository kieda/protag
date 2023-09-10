package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractProtagNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IProtagRegex;

import java.util.List;

public final class ProtagStringRegexImpl extends AbstractProtagNode implements IProtagRegex {
    private final String value;
    public ProtagStringRegexImpl(String value) {
        this.value = value;
    }
    @Override
    public String getRawValue() {
        return value;
    }

    @Override
    public List<CProtagNode> getChildren() {
        return List.of();
    }
}
