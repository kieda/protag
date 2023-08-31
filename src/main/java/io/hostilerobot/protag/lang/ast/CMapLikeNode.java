package io.hostilerobot.protag.lang.ast;

import java.util.List;

// represents a list of PairLikeNodes
public interface CMapLikeNode extends CListLikeNode {
    @Override
    List<? extends CPairLikeNode> getItems();
}
