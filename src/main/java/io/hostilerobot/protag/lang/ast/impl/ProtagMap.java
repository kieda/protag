package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.CPairLikeNode;
import io.hostilerobot.protag.lang.ast.IProtagMap;
import io.hostilerobot.protag.lang.ast.IProtagPair;

import java.util.LinkedList;

public final class ProtagMap extends AbstractListLikeNode<IProtagPair> implements IProtagMap {
    public ProtagMap(LinkedList<IProtagPair> entries) {
        super(entries);
    }
}
