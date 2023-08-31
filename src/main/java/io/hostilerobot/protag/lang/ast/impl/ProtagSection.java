package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagSection;
import io.hostilerobot.protag.lang.ast.IProtagSequence;
import io.hostilerobot.protag.lang.ast.ProtagNode;

public record ProtagSection(ProtagNode parent, ProtagNode name, IProtagSequence items) implements IProtagSection {
    @Override
    public ProtagNode getKey() {
        return name;
    }

    @Override
    public IProtagSequence getValue() {
        return items;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
