package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractProtagNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IProtagPair;

import java.util.List;

public final class ProtagPairImpl extends AbstractProtagNode implements IProtagPair {
    private final CProtagNode key;
    private final CProtagNode value;
    public ProtagPairImpl(CProtagNode key, CProtagNode value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public CProtagNode getKey() {
        return key;
    }

    @Override
    public CProtagNode getValue() {
        return value;
    }

    @Override
    public List<CProtagNode> getChildren() {
        return List.of(key, value);
    }
}
