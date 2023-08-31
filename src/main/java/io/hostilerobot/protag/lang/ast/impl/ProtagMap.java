package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.CPairLikeNode;
import io.hostilerobot.protag.lang.ast.IProtagMap;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public record ProtagMap(ProtagNode parent, List<CPairLikeNode> mapItems) implements IProtagMap {
    @Override
    public List<CPairLikeNode> getItems() {
        return mapItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
