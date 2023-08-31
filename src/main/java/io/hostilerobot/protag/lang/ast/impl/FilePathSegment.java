package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IFilePathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public record FilePathSegment(ProtagNode parent, List<ProtagNode> pathSegmentItems) implements IFilePathSegment {
    @Override
    public List<ProtagNode> getItems() {
        return pathSegmentItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
