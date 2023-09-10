package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IFilePathSegment;
import io.hostilerobot.protag.ast.ProtagNode;

import java.util.LinkedList;

public final class FilePathSegment extends AbstractListLikeNode<ProtagNode> implements IFilePathSegment {
    public FilePathSegment(LinkedList<ProtagNode> segmentItems) {
        super(segmentItems);
    }
}
