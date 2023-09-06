package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagRegex;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

public final class ProtagStringRegex extends AbstractProtagNode implements IProtagRegex {
    private final String value;
    public ProtagStringRegex(ASTMetaInfo meta, String value) {
        super(meta);
        this.value = value;
    }
    @Override
    public String getRawValue() {
        return value;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of();
    }
}
