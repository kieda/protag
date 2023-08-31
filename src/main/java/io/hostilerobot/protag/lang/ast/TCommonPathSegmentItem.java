package io.hostilerobot.protag.lang.ast;

// CommonPathSegment = REGEX|LITERAL|List|Map|Precedence
public sealed interface TCommonPathSegmentItem extends TProtagPathSegmentItem, TFilePathSegmentItem, TJavaPathSegmentItem
    permits IProtagRegex, LProtagLiteral, IProtagList, IProtagMap, IProtagPrecedence{
}
