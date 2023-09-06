package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagSection;
import io.hostilerobot.protag.lang.ast.IProtagSequence;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

public final class ProtagSection extends AbstractProtagNode implements IProtagSection {
    private final ProtagNode name;
    private final IProtagSequence items;
    public ProtagSection(ASTMetaInfo meta, ProtagNode name, IProtagSequence items) {
        super(meta);
        this.name = name;
        this.items = items;
    }

    @Override
    public ProtagNode getKey() {
        return name;
    }

    @Override
    public IProtagSequence getValue() {
        return items;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of(name, items);
    }
}
