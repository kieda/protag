package io.hostilerobot.protag.ast;

// all of these items can be found in a path
// CommonPath = REGEX|LITERAL|List|Map|Precedence
public sealed interface TCommonPathItem extends TProtagPathItem, TFilePathItem, TJavaPathItem
        permits IProtagRegex, LProtagLiteral, IProtagList, IProtagMap, IProtagPrecedence {
}
