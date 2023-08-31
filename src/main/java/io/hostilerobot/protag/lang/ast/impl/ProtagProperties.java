package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagProperties;
import io.hostilerobot.protag.lang.ast.IProtagSection;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public record ProtagProperties(ProtagNode parent, List<IProtagSection> properties) implements IProtagProperties {
    @Override
    public List<IProtagSection> getItems() {
        return properties;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
