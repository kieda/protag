package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

// base class for all nodes in the AST
public interface ProtagNode {
    default boolean isLeaf() {
        return !(this instanceof CListLikeNode);
    }
    //
    List<ProtagNode> getChildren();
}
