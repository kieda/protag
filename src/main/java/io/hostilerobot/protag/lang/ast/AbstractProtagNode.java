package io.hostilerobot.protag.lang.ast;

import java.util.List;

public abstract class AbstractProtagNode implements ProtagNode {
    public AbstractProtagNode() {
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + getChildren();
    }
}
