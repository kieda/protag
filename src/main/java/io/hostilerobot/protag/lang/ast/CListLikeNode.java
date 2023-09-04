package io.hostilerobot.protag.lang.ast;

import java.util.Deque;

// represents a
public interface CListLikeNode extends ProtagNode {
    Deque<? extends ProtagNode> getItems();
}
