package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IProtagSequence;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class ProtagSequence extends AbstractListLikeNode<ProtagNode> implements IProtagSequence {
    public ProtagSequence(ASTMetaInfo meta) {
        super(meta);
    }
}
