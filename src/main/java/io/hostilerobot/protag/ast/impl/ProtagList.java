package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IProtagList;
import io.hostilerobot.protag.ast.ProtagNode;

import java.util.LinkedList;

public final class ProtagList extends AbstractListLikeNode<ProtagNode> implements IProtagList {
    public ProtagList(LinkedList<ProtagNode> listItems) {
        super(listItems);
    }
}
