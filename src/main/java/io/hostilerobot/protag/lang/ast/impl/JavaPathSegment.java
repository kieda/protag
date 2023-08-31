package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IJavaPathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public record JavaPathSegment(ProtagNode parent, List<ProtagNode> pathSegmentItems) implements IJavaPathSegment {
    @Override
    public List<ProtagNode> getItems() {
        return pathSegmentItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
