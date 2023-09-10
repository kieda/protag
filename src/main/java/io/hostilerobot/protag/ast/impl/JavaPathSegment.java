package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IJavaPathSegment;
import io.hostilerobot.protag.ast.ProtagNode;

import java.util.LinkedList;

public final class JavaPathSegment extends AbstractListLikeNode<ProtagNode> implements IJavaPathSegment {
    public JavaPathSegment(LinkedList<ProtagNode> segment) {
        super(segment);
    }
}
