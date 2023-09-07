package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagTransitionLeft;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public final class ProtagTransitionLeft extends AbstractProtagNode implements IProtagTransitionLeft {
    private final ProtagNode to;
    private final ProtagNode from;
    public ProtagTransitionLeft(ProtagNode to, ProtagNode from) {
        this.to = to;
        this.from = from;
    }
    @Override
    public ProtagNode getKey() {
        return to;
    }

    @Override
    public ProtagNode getValue() {
        return from;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of(to, from);
    }
}
