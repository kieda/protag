package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IJavaPath;
import io.hostilerobot.protag.ast.IJavaPathSegment;

import java.util.LinkedList;

public final class JavaPathImpl extends AbstractListLikeNode<IJavaPathSegment> implements IJavaPath {
    public JavaPathImpl(LinkedList<IJavaPathSegment> segments) {
        super(segments);
    }
}
