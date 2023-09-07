package io.hostilerobot.protag.lang.ast;

public non-sealed interface IFilePath extends CPathNode {

    @Override
    default EPathType getPathType() {
        return EPathType.FILE;
    }
}
