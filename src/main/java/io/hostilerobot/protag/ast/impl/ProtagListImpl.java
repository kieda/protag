package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IProtagList;

import java.util.LinkedList;

public final class ProtagListImpl extends AbstractListLikeNode<CProtagNode> implements IProtagList {
    public ProtagListImpl(LinkedList<CProtagNode> listItems) {
        super(listItems);
    }
}
