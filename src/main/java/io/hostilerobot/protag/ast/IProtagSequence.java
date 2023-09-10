package io.hostilerobot.protag.ast;

import java.util.Deque;

public interface IProtagSequence extends CListLikeNode {
    @Override
    Deque<ProtagNode> getItems();
}
