package io.hostilerobot.protag.ast;

public non-sealed interface IFilePath extends CPathNode {

    @Override
    default EPathType getPathType() {
        return EPathType.FILE;
    }
}
