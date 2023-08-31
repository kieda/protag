package io.hostilerobot.protag.lang.ast;

import org.apache.commons.math.fraction.Fraction;

public record LProtagInteger(int val) implements CNumberNode {
    @Override
    public EProtagNumberType getType() {
        return EProtagNumberType.INTEGER;
    }

    @Override
    public double toReal() {
        return (double)val;
    }

    @Override
    public int toInteger() {
        return val;
    }

    @Override
    public Fraction toQuotient() {
        return new Fraction(val);
    }

    @Override
    public ProtagNode getParent() {
        return null;
    }
}
