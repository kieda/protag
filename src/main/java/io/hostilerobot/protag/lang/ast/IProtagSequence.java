package io.hostilerobot.protag.lang.ast;

import java.util.Deque;

public interface IProtagSequence extends CListLikeNode {
    @Override
    Deque<ProtagNode> getItems();
}
