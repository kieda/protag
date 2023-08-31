package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagTransitionRight;
import io.hostilerobot.protag.lang.ast.ProtagNode;

public record ProtagTransitionRight(ProtagNode parent, ProtagNode from, ProtagNode to) implements IProtagTransitionRight {
    @Override
    public ProtagNode getKey() {
        return from;
    }

    @Override
    public ProtagNode getValue() {
        return to;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
