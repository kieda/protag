package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IJavaPath;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.List;

public record JavaPath(ProtagNode parent, Deque<ProtagNode> pathItems) implements IJavaPath {
    @Override
    public Deque<? extends ProtagNode> getItems() {
        return pathItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
