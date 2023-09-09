package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IFilePathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.LinkedList;

public final class FilePathSegment extends AbstractListLikeNode<ProtagNode> implements IFilePathSegment {
    public FilePathSegment(LinkedList<ProtagNode> segmentItems) {
        super(segmentItems);
    }
}
