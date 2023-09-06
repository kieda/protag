package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IJavaPathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class JavaPathSegment extends AbstractListLikeNode<ProtagNode> implements IJavaPathSegment {
    public JavaPathSegment(ASTMetaInfo meta) {
        super(meta);
    }
}
