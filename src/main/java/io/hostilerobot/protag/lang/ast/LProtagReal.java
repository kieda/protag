package io.hostilerobot.protag.lang.ast;

import org.apache.commons.math.fraction.Fraction;

public record LProtagReal(ProtagNode parent, double val) implements CNumberNode {
    @Override
    public EProtagNumberType getType() {
        return EProtagNumberType.REAL;
    }

    @Override
    public double toReal() {
        return 0;
    }

    @Override
    public int toInteger() {
        return 0;
    }

    @Override
    public Fraction toQuotient() {
        return null;
    }

    public ProtagNode getParent() {
        return parent;
    }
}
