package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;
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
}
