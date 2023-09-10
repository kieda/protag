package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IProtagPath;
import io.hostilerobot.protag.ast.IProtagPathSegment;

import java.util.LinkedList;

public final class ProtagPathImpl extends AbstractListLikeNode<IProtagPathSegment> implements IProtagPath {
    public ProtagPathImpl(LinkedList<IProtagPathSegment> pathSegments) {
        super(pathSegments);
    }
}
