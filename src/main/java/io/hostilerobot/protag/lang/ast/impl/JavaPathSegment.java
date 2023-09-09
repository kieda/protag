package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IJavaPathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.LinkedList;

public final class JavaPathSegment extends AbstractListLikeNode<ProtagNode> implements IJavaPathSegment {
    public JavaPathSegment(LinkedList<ProtagNode> segment) {
        super(segment);
    }
}
