package io.hostilerobot.protag.lang.ast;

// base class for all nodes in the AST
public interface ProtagNode {
//    void setParent(ProtagNode node);
    ProtagNode getParent();
}
