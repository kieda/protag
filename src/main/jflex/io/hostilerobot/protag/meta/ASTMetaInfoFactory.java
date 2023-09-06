package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ProtagSymbol;
import io.hostilerobot.protag.lang.ast.ProtagNode;

public interface ASTMetaInfoFactory<T extends ASTMetaInfo> {
    // what we want: a table of
    // ProtagMetaTable = Map Node -> ASTMetaInfo
    //    + getFactory()
    public T createInfo(ProtagNode node, ProtagSymbol symbol);
}
