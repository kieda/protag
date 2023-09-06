package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IFilePathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

public final class FilePathSegment extends AbstractListLikeNode<ProtagNode> implements IFilePathSegment {
    public FilePathSegment(ASTMetaInfo meta) {
        super(meta);
    }
}
