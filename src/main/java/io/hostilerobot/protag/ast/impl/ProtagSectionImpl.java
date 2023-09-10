package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractProtagNode;
import io.hostilerobot.protag.ast.CProtagNode;
import io.hostilerobot.protag.ast.IProtagSection;
import io.hostilerobot.protag.ast.IProtagSequence;

import java.util.List;

public final class ProtagSectionImpl extends AbstractProtagNode implements IProtagSection {
    private final CProtagNode name;
    private final IProtagSequence items;
    public ProtagSectionImpl(CProtagNode name, IProtagSequence items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public CProtagNode getKey() {
        return name;
    }

    @Override
    public IProtagSequence getValue() {
        return items;
    }

    @Override
    public List<CProtagNode> getChildren() {
        return List.of(name, items);
    }
}
