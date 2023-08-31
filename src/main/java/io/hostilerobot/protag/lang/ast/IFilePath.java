package io.hostilerobot.protag.lang.ast;

import java.util.List;

public non-sealed interface IFilePath extends CPathNode {

    @Override
    default EPathType getPathType() {
        return EPathType.FILE;
    }
}
