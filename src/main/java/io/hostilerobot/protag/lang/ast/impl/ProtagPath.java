package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagPath;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public record ProtagPath(ProtagNode parent, List<ProtagNode> pathItems) implements IProtagPath {
    @Override
    public List<? extends ProtagNode> getItems() {
        return pathItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
