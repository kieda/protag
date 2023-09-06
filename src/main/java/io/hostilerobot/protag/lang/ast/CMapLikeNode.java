package io.hostilerobot.protag.lang.ast;

import java.util.Deque;

// represents a list of PairLikeNodes
public interface CMapLikeNode extends CListLikeNode {
    @Override
    Deque<? extends CPairLikeNode> getItems();
}
