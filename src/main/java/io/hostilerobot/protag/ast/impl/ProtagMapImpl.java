package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IProtagMap;
import io.hostilerobot.protag.ast.IProtagPair;

import java.util.LinkedList;

public final class ProtagMapImpl extends AbstractListLikeNode<IProtagPair> implements IProtagMap {
    public ProtagMapImpl(LinkedList<IProtagPair> entries) {
        super(entries);
    }
}
