package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagProperties;
import io.hostilerobot.protag.lang.ast.IProtagSection;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.List;

public record ProtagProperties(ProtagNode parent, Deque<IProtagSection> properties) implements IProtagProperties {
    @Override
    public Deque<? extends ProtagNode> getItems() {
        return properties;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
