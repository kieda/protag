package io.hostilerobot.protag.ast;

public non-sealed interface IProtagPath extends CPathNode {
    @Override
    default EPathType getPathType() {
        return EPathType.PROTAG;
    }
}
