package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractProtagNode;
import io.hostilerobot.protag.ast.IProtagPrecedence;
import io.hostilerobot.protag.ast.ProtagNode;

import java.util.List;

public final class ProtagPrecedence extends AbstractProtagNode implements IProtagPrecedence {
    private final ProtagNode child;
    public ProtagPrecedence(ProtagNode child) {
        this.child = child;
    }

    @Override
    public ProtagNode getChild() {
        return child;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of(child);
    }
}
