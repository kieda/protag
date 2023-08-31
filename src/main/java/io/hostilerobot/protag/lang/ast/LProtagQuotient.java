package io.hostilerobot.protag.lang.ast;

import org.apache.commons.math.fraction.Fraction;

public record LProtagQuotient(ProtagNode parent, Fraction val) implements CNumberNode {
    @Override
    public EProtagNumberType getType() {
        return EProtagNumberType.QUOTIENT;
    }

    @Override
    public double toReal() {
        return getType().toReal(val);
    }

    @Override
    public int toInteger() {
        return getType().toInteger(val);
    }

    @Override
    public Fraction toQuotient() {
        return getType().toQuotient(val);
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
