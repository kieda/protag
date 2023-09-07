package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagPrecedence;
import io.hostilerobot.protag.lang.ast.ProtagNode;

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
