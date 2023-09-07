package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;
import org.apache.commons.math.fraction.Fraction;

public final class LProtagInteger extends AbstractProtagNode implements CNumberNode {
    private final int val;
    public LProtagInteger(int val) {
        this.val = val;
    }
    @Override
    public EProtagNumberType getType() {
        return EProtagNumberType.INTEGER;
    }

    @Override
    public double toReal() {
        return val;
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
    public String toString() {
        return "%s[%d]".formatted(getClass().getSimpleName(), val);
    }
}
