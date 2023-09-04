package io.hostilerobot.protag.lang.ast;

import org.apache.commons.math.fraction.Fraction;

/**
 * represents a natural number
 */
public record LProtagNatural(ProtagNode parent, int nat) implements CNumberNode, TProtagPathItem, TProtagPathSegmentItem {
    @Override
    public EProtagNumberType getType() {
        return EProtagNumberType.INTEGER;
    }

    @Override
    public double toReal() {
        return (double) nat;
    }

    @Override
    public int toInteger() {
        return nat;
    }

    @Override
    public Fraction toQuotient() {
        return new Fraction(nat, 1);
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
