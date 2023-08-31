package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagSequence;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public record ProtagSequence(ProtagNode parent, List<ProtagNode> items) implements IProtagSequence {
    @Override
    public List<ProtagNode> getItems() {
        return items;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
