package io.hostilerobot.protag.lang.ast;

import java.util.List;

// represents a
public interface CListLikeNode extends ProtagNode {
    List<? extends ProtagNode> getItems();
}
