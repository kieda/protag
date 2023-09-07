package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

public abstract class AbstractProtagNode implements ProtagNode {
    private ProtagNode parent;

    public AbstractProtagNode() {
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }

    @Override
    public void setParent(ProtagNode node) {
        this.parent = node;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + getChildren();
    }
}
