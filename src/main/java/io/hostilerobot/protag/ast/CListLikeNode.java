package io.hostilerobot.protag.ast;

import java.util.Deque;

// represents a
public interface CListLikeNode extends CProtagNode {
    Deque<? extends CProtagNode> getItems();
}
