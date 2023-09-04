package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagPathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.List;

public record ProtagPathSegment(ProtagNode parent, Deque<ProtagNode> pathSegmentItems) implements IProtagPathSegment {
    @Override
    public Deque<? extends ProtagNode> getItems() {
        return pathSegmentItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
