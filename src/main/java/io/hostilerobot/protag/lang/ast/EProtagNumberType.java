package io.hostilerobot.protag.lang.ast;

import org.apache.commons.math.fraction.Fraction;
import org.apache.commons.math.fraction.FractionConversionException;

public enum EProtagNumberType {
    INTEGER,
    QUOTIENT,
    REAL;
    public double toReal(Number val) {
        return val.doubleValue();
    }
    public int toInteger(Number val) {
        return val.intValue();
    }
    public Fraction toQuotient(Number val) {
        return switch (val) {
            case Integer i -> new Fraction(i);
            case Fraction f -> f;
            default -> {
                try {
                    yield new Fraction(val.doubleValue());
                } catch (FractionConversionException e) {
                    throw new FractionConversionRuntimeException(e);
                }
            }
        };
    }
}
