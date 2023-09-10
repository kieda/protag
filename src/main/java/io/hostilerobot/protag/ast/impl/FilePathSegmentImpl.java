package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IFilePathSegment;

import java.util.LinkedList;

public final class FilePathSegmentImpl extends AbstractListLikeNode<CProtagNode> implements IFilePathSegment {
    public FilePathSegmentImpl(LinkedList<CProtagNode> segmentItems) {
        super(segmentItems);
    }
}
