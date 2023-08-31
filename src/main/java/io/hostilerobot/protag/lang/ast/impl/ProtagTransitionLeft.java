package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagTransitionLeft;
import io.hostilerobot.protag.lang.ast.ProtagNode;

public record ProtagTransitionLeft(ProtagNode parent, ProtagNode to, ProtagNode from) implements IProtagTransitionLeft {
    @Override
    public ProtagNode getKey() {
        return to;
    }

    @Override
    public ProtagNode getValue() {
        return from;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
