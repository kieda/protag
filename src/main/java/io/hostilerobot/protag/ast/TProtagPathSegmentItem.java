package io.hostilerobot.protag.ast;

/**
 * tagging interface to represent that this node may be part of a protag path segment
 *
 * These are:
 *     PNAME|PBODY|NATURAL|(REGEX|LITERAL|List|Map|Precedence)
 */
public sealed interface TProtagPathSegmentItem permits TCommonPathSegmentItem, LProtagName, LProtagBody, LProtagNatural {
}