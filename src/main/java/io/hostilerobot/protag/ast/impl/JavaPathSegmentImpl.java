package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IJavaPathSegment;

import java.util.LinkedList;

public final class JavaPathSegmentImpl extends AbstractListLikeNode<CProtagNode> implements IJavaPathSegment {
    public JavaPathSegmentImpl(LinkedList<CProtagNode> segment) {
        super(segment);
    }
}
