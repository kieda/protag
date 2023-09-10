package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractProtagNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IProtagTransitionRight;

import java.util.List;

public final class ProtagTransitionRightImpl extends AbstractProtagNode implements IProtagTransitionRight {
    private final CProtagNode from;
    private final CProtagNode to;
    public ProtagTransitionRightImpl(CProtagNode from, CProtagNode to) {
        this.from = from;
        this.to = to;
    }
    @Override
    public CProtagNode getKey() {
        return from;
    }

    @Override
    public CProtagNode getValue() {
        return to;
    }

    @Override
    public List<CProtagNode> getChildren() {
        return List.of(from, to);
    }
}
