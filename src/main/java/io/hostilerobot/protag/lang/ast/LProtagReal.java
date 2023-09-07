package io.hostilerobot.protag.lang.ast;

import org.apache.commons.math.fraction.Fraction;
import org.apache.commons.math.fraction.FractionConversionException;

public final class LProtagReal extends AbstractProtagNode implements CNumberNode {
    private final double val;
    public LProtagReal(double val) {
        this.val = val;
    }
    @Override
    public EProtagNumberType getType() {
        return EProtagNumberType.REAL;
    }

    @Override
    public double toReal() {
        return val;
    }

    @Override
    public int toInteger() {
        return (int)val;
    }

    @Override
    public Fraction toQuotient() {
        try {
            return new Fraction(val);
        } catch (FractionConversionException e) {
            throw new FractionConversionRuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(getClass().getSimpleName(), Double.toString(val));
    }
}
