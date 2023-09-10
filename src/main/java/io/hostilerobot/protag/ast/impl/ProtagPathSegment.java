package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IProtagPathSegment;
import io.hostilerobot.protag.ast.ProtagNode;

import java.util.LinkedList;

public final class ProtagPathSegment extends AbstractListLikeNode<ProtagNode> implements IProtagPathSegment {
    public ProtagPathSegment(LinkedList<ProtagNode> segmentItems) {
        super(segmentItems);
    }
}
