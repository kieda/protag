package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;
import org.apache.commons.math.fraction.Fraction;
import org.apache.commons.math.fraction.FractionConversionException;

public final class LProtagReal extends AbstractProtagNode implements CNumberNode {
    private final double val;
    public LProtagReal(ASTMetaInfo meta, double val) {
        super(meta);
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
}
