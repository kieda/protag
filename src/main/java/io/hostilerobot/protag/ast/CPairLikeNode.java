package io.hostilerobot.protag.ast;

/**
 * used to represent nodes that have a pair-like semantics
 *
 */
public sealed interface CPairLikeNode extends CProtagNode
    permits IProtagSection, IProtagPair, IProtagTransitionLeft, IProtagTransitionRight{
    CProtagNode getKey();
    CProtagNode getValue();
}
