package io.hostilerobot.protag.lang.ast;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


// todo - should we remove getChildren() or getItems(), or just keep it as is to access the fields in multiple ways??
public abstract class AbstractListLikeNode<T extends ProtagNode> extends AbstractProtagNode implements CListLikeNode{
    private final LinkedList<T> items;
    public AbstractListLikeNode() {
        this.items = new LinkedList<>();
    }
    public AbstractListLikeNode(LinkedList<T> items) {
        this.items = items;
    }

    @Override
    public Deque<T> getItems() {
        return items;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return (List<ProtagNode>)items;
    }
}
