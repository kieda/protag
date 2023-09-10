package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractProtagNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IProtagTransitionLeft;

import java.util.List;

public final class ProtagTransitionLeftImpl extends AbstractProtagNode implements IProtagTransitionLeft {
    private final CProtagNode to;
    private final CProtagNode from;
    public ProtagTransitionLeftImpl(CProtagNode to, CProtagNode from) {
        this.to = to;
        this.from = from;
    }
    @Override
    public CProtagNode getKey() {
        return to;
    }

    @Override
    public CProtagNode getValue() {
        return from;
    }

    @Override
    public List<CProtagNode> getChildren() {
        return List.of(to, from);
    }
}
