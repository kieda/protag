package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagPath;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.List;

public record ProtagPath(ProtagNode parent, Deque<ProtagNode> pathItems) implements IProtagPath {
    @Override
    public Deque<? extends ProtagNode> getItems() {
        return pathItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
