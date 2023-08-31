package io.hostilerobot.protag.lang.ast;

public non-sealed interface IProtagPrecedence extends ProtagNode, TCommonPathSegmentItem, TCommonPathItem {
    public ProtagNode getChild();
}
