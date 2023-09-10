package io.hostilerobot.protag.ast;

import org.apache.commons.math.fraction.Fraction;

/**
 * represents a natural number
 */
public final class LProtagNatural extends AbstractProtagNode implements CNumberNode, TProtagPathItem, TProtagPathSegmentItem {
    private final int nat;
    public LProtagNatural(int nat) {
        this.nat = nat;
    }
    @Override
    public EProtagNumberType getType() {
        return EProtagNumberType.INTEGER;
    }

    @Override
    public double toReal() {
        return nat;
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
    public String toString() {
        return "%s[%s]".formatted(getClass().getSimpleName(), nat);
    }
}
