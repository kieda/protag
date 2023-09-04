package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagList;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.List;

public record ProtagList(ProtagNode parent, Deque<ProtagNode> listItems) implements IProtagList {
    @Override
    public Deque<? extends ProtagNode> getItems() {
        return listItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
