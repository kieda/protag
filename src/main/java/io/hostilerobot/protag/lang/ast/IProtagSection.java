package io.hostilerobot.protag.lang.ast;

public non-sealed interface IProtagSection extends CPairLikeNode {
    @Override
    IProtagSequence getValue();
}
