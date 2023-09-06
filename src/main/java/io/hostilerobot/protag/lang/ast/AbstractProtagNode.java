package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

public abstract class AbstractProtagNode implements ProtagNode {
    private final ASTMetaInfo meta;
    private ProtagNode parent;

    public AbstractProtagNode(ASTMetaInfo info) {
        meta = info;
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
    public ASTMetaInfo getMetaInfo() {
        return meta;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of();
    }
}
