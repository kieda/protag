package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IFilePath;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.List;

public record FilePath(ProtagNode parent, Deque<ProtagNode> pathSegmentItems) implements IFilePath {
    @Override
    public Deque<? extends ProtagNode> getItems() {
        return pathSegmentItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
