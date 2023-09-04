package io.hostilerobot.protag.lang;

import io.hostilerobot.protag.lang.ast.ESignType;

/**
 * prefixes with X - only used as a stepping stone in AST creation
 * Placed in lang package and package scoped.
 * @param integerPart
 * @param fractionalPart
 */
record XDecimal(int integerPart, int fractionalPart) {
    private static final int POW10_1   = 10;
    private static final int POW10_2   = 100;
    private static final int POW10_3   = 1000;
    private static final int POW10_4   = 10000;
    private static final int POW10_5   = 100000;
    private static final int POW10_6   = 1000000;
    private static final int POW10_7   = 10000000;
    private static final int POW10_8   = 100000000;
    private static final int POW10_9   = 1000000000;
    private static final long POW10_10 = 10000000000L;
    private static double fractionalDouble(int fractionalPart) {
        if (fractionalPart < POW10_5) { // 1 to 5
            if (fractionalPart < POW10_2) { // 1 or 2
                if (fractionalPart < POW10_1) return fractionalPart / (double) POW10_1;
                return fractionalPart / (double) POW10_2;
            }
            else { // 3, 4 or 5
                if (fractionalPart < POW10_3) return fractionalPart / (double) POW10_3;
                if (fractionalPart < POW10_4) return fractionalPart / (double) POW10_4;
                return fractionalPart / (double) POW10_5;
            }
        }
        else { // 6 to 7
            if (fractionalPart < POW10_7) { // 6 or 7
                if (fractionalPart < POW10_6) return fractionalPart / (double) POW10_6;
                return fractionalPart / (double) POW10_7;
            }
            else { // 8, 9 or 10
                if (fractionalPart < POW10_8) return fractionalPart / (double) POW10_8;
                if (fractionalPart < POW10_9) return fractionalPart / (double) POW10_9;
                return fractionalPart / (double)POW10_10;
            }
        }
    }
    public double toDouble(ESignType sign) {
        if(fractionalPart == 0)
            return sign.negate((double)integerPart);
        // 1231234 .
        // 1231234 / 1231234
        return sign.negate(integerPart + fractionalDouble(fractionalPart));
    }
}
