package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagPair;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public final class ProtagPair extends AbstractProtagNode implements IProtagPair {
    private final ProtagNode key;
    private final ProtagNode value;
    public ProtagPair(ProtagNode key, ProtagNode value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public ProtagNode getKey() {
        return key;
    }

    @Override
    public ProtagNode getValue() {
        return value;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of(key, value);
    }
}
