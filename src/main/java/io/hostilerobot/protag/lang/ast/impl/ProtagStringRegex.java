package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagRegex;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public final class ProtagStringRegex extends AbstractProtagNode implements IProtagRegex {
    private final String value;
    public ProtagStringRegex(String value) {
        this.value = value;
    }
    @Override
    public String getRawValue() {
        return value;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of();
    }
}
