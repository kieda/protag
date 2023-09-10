package io.hostilerobot.protag.ast;

import java.util.List;

// base class for all nodes in the AST
public interface CProtagNode {
    default boolean isLeaf() {
        return !(this instanceof CListLikeNode);
    }
    //
    List<CProtagNode> getChildren();
}
