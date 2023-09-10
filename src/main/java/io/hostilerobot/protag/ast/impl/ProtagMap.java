package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IProtagMap;
import io.hostilerobot.protag.ast.IProtagPair;

import java.util.LinkedList;

public final class ProtagMap extends AbstractListLikeNode<IProtagPair> implements IProtagMap {
    public ProtagMap(LinkedList<IProtagPair> entries) {
        super(entries);
    }
}
