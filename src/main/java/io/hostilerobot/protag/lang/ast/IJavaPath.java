package io.hostilerobot.protag.lang.ast;

public non-sealed interface IJavaPath extends CPathNode {
    @Override
    default EPathType getPathType() {
        return EPathType.JAVA;
    }
}
