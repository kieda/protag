package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagList;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public record ProtagList(ProtagNode parent, List<ProtagNode> listItems) implements IProtagList {
    @Override
    public List<ProtagNode> getItems() {
        return listItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
