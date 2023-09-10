package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractProtagNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IProtagPrecedence;

import java.util.List;

public final class ProtagPrecedenceImpl extends AbstractProtagNode implements IProtagPrecedence {
    private final CProtagNode child;
    public ProtagPrecedenceImpl(CProtagNode child) {
        this.child = child;
    }

    @Override
    public CProtagNode getChild() {
        return child;
    }

    @Override
    public List<CProtagNode> getChildren() {
        return List.of(child);
    }
}
