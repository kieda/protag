package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IJavaPath;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class JavaPath extends AbstractListLikeNode<ProtagNode> implements IJavaPath {
    public JavaPath(ASTMetaInfo meta) {
        super(meta);
    }
}
