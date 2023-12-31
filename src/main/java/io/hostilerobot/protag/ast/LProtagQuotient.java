package io.hostilerobot.protag.ast;

import org.apache.commons.math.fraction.Fraction;

public final class LProtagQuotient extends AbstractProtagNode implements CNumberNode {
    private final Fraction val;
    public LProtagQuotient(Fraction val) {
        this.val = val;
    }
    @Override
    public EProtagNumberType getType() {
        return EProtagNumberType.QUOTIENT;
    }

    @Override
    public double toReal() {
        return val.doubleValue();
    }

    @Override
    public int toInteger() {
        return val.intValue();
    }

    @Override
    public Fraction toQuotient() {
        return val;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(getClass().getSimpleName(), val);
    }
}
