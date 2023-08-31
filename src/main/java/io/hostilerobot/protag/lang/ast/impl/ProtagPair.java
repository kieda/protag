package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagPair;
import io.hostilerobot.protag.lang.ast.ProtagNode;

public record ProtagPair(ProtagNode parent, ProtagNode key, ProtagNode value) implements IProtagPair {
    @Override
    public ProtagNode getKey() {
        return key;
    }

    @Override
    public ProtagNode getValue() {
        return value;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
