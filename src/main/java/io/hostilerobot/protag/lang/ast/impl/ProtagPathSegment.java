package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IProtagPathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class ProtagPathSegment extends AbstractListLikeNode<ProtagNode> implements IProtagPathSegment {
    public ProtagPathSegment(ASTMetaInfo meta) {
        super(meta);
    }
}
