package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagRegex;
import io.hostilerobot.protag.lang.ast.ProtagNode;

public record ProtagStringRegex(ProtagNode parent, String value) implements IProtagRegex {
    @Override
    public String getRawValue() {
        return value;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
