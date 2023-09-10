package io.hostilerobot.protag.ast;

import java.util.List;

public abstract class AbstractProtagNode implements CProtagNode {
    public AbstractProtagNode() {
    }

    @Override
    public List<CProtagNode> getChildren() {
        return List.of();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + getChildren();
    }
}
