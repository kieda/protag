package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;
import io.hostilerobot.protag.util.MirrorDeque;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


// todo - remove getChildren() or getItems()
public abstract class AbstractListLikeNode<T extends ProtagNode> extends AbstractProtagNode implements CListLikeNode{
    /*
     * ONE WAY MIRRORED: elements added to items will be reflected in children
     *                   elements added to children will be independent from items
     * USAGE: children contain items, along with the comments in the AST
     *        items contain data-based nodes that are part of the AST
     *
     * items can only be added to, and removing items throws an exception
     */
    private final List<ProtagNode> children;
    private final Deque<T> items;
    public AbstractListLikeNode(ASTMetaInfo info) {
        super(info);

        this.children = new LinkedList<>();
        this.items = new MirrorDeque<>(new LinkedList<>(), children);
    }

    @Override
    public Deque<T> getItems() {
        return items;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return children;
    }
}
