package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IJavaPath;
import io.hostilerobot.protag.lang.ast.IJavaPathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.LinkedList;
import java.util.List;

public final class JavaPath extends AbstractListLikeNode<IJavaPathSegment> implements IJavaPath {
    public JavaPath(LinkedList<IJavaPathSegment> segments) {
        super(segments);
    }
}
