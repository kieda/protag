package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IProtagPath;
import io.hostilerobot.protag.lang.ast.IProtagPathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.LinkedList;

public final class ProtagPath extends AbstractListLikeNode<IProtagPathSegment> implements IProtagPath {
    public ProtagPath(LinkedList<IProtagPathSegment> pathSegments) {
        super(pathSegments);
    }
}
