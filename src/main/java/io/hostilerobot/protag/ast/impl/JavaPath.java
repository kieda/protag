package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IJavaPath;
import io.hostilerobot.protag.ast.IJavaPathSegment;

import java.util.LinkedList;

public final class JavaPath extends AbstractListLikeNode<IJavaPathSegment> implements IJavaPath {
    public JavaPath(LinkedList<IJavaPathSegment> segments) {
        super(segments);
    }
}
