package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagSequence;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.List;

public record ProtagSequence(ProtagNode parent, Deque<ProtagNode> items) implements IProtagSequence {
    @Override
    public Deque<ProtagNode> getItems() {
        return items;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
