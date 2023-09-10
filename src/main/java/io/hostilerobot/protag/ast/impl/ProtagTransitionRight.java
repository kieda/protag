package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractProtagNode;
import io.hostilerobot.protag.ast.IProtagTransitionRight;
import io.hostilerobot.protag.ast.ProtagNode;

import java.util.List;

public final class ProtagTransitionRight extends AbstractProtagNode implements IProtagTransitionRight {
    private final ProtagNode from;
    private final ProtagNode to;
    public ProtagTransitionRight(ProtagNode from, ProtagNode to) {
        this.from = from;
        this.to = to;
    }
    @Override
    public ProtagNode getKey() {
        return from;
    }

    @Override
    public ProtagNode getValue() {
        return to;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of(from, to);
    }
}
