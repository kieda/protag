package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IProtagList;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.LinkedList;

public final class ProtagList extends AbstractListLikeNode<ProtagNode> implements IProtagList {
    public ProtagList(LinkedList<ProtagNode> listItems) {
        super(listItems);
    }
}
