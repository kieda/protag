package io.hostilerobot.protag.ast;

import org.apache.commons.math.fraction.Fraction;

public sealed interface CNumberNode extends CProtagNode permits LProtagReal, LProtagQuotient, LProtagInteger, LProtagNatural {
    EProtagNumberType getType();

    double toReal();
    int toInteger();
    Fraction toQuotient();
}
