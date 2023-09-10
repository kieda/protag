package io.hostilerobot.protag.ast;

/**
 * used to represent nodes that have a pair-like semantics
 *
 */
public sealed interface CPairLikeNode extends ProtagNode
    permits IProtagSection, IProtagPair, IProtagTransitionLeft, IProtagTransitionRight{
    ProtagNode getKey();
    ProtagNode getValue();
}
