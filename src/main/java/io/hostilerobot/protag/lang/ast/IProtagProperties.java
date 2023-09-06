package io.hostilerobot.protag.lang.ast;

import java.util.Deque;

// properties is a list of sections
public interface IProtagProperties extends CMapLikeNode {
    @Override
    Deque<IProtagSection> getItems();
}
