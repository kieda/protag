package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ProtagSymbol;
import io.hostilerobot.protag.lang.ast.CCommentNode;

public interface ASTMetaInfoBuilder<T extends ASTMetaInfo> {

    public void addComment(CCommentNode node);
    public void addSep(ProtagSymbol symbol);
    public T build(ProtagSymbol symbol);
    public boolean isBuilt();
}
