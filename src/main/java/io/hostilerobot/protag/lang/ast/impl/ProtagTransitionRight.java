package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagTransitionRight;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

public final class ProtagTransitionRight extends AbstractProtagNode implements IProtagTransitionRight {
    private final ProtagNode from;
    private final ProtagNode to;
    public ProtagTransitionRight(ASTMetaInfo meta, ProtagNode from, ProtagNode to) {
        super(meta);
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
