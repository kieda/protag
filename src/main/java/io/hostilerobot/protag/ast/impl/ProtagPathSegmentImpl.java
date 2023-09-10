package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IProtagPathSegment;

import java.util.LinkedList;

public final class ProtagPathSegmentImpl extends AbstractListLikeNode<CProtagNode> implements IProtagPathSegment {
    public ProtagPathSegmentImpl(LinkedList<CProtagNode> segmentItems) {
        super(segmentItems);
    }
}
