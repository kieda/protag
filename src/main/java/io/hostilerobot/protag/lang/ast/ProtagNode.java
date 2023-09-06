package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

// base class for all nodes in the AST
public interface ProtagNode {
    /**
     * Contains meta-information for this node. Meta info should not change the way we process or handle protag
     * E.g. comments, line numbers, etc
     *
     * @return the meta info
     */
    ASTMetaInfo getMetaInfo();
    void setParent(ProtagNode node);
    ProtagNode getParent();
    default boolean isLeaf() {
        return !(this instanceof CListLikeNode);
    }
    //
    List<ProtagNode> getChildren();
}
