package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagPrecedence;
import io.hostilerobot.protag.lang.ast.ProtagNode;

public record ProtagPrecedence(ProtagNode parent, ProtagNode child) implements IProtagPrecedence {
    @Override
    public ProtagNode getParent() {
        return parent;
    }

    @Override
    public ProtagNode getChild() {
        return child;
    }
}
