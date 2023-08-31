package io.hostilerobot.protag.lang.ast;

/**
 * represents items that can be found in a protag path
 *
 * These are:
 *  *     PNAME|PathSegment|NATURAL|(REGEX|LITERAL|List|Map|Precedence)
 */
public sealed interface TProtagPathItem permits IProtagPathSegment, LProtagName, LProtagNatural, TCommonPathItem {
}
