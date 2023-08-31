package io.hostilerobot.protag.lang.ast;

import java.util.List;

public non-sealed interface IProtagPath extends CPathNode {
    @Override
    default EPathType getPathType() {
        return EPathType.PROTAG;
    }
}
