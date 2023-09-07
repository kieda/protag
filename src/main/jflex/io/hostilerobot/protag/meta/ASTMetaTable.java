package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ProtagSymbol;
import io.hostilerobot.protag.lang.ast.ProtagNode;

public interface ASTMetaTable<A extends ASTMetaInfo, B extends ASTMetaInfoBuilder<A>> {
    public B createBuilder(ProtagNode node);
    public B getBuilder(ProtagNode node);
    public A get(ProtagNode node);
    public A get(ProtagSymbol node);
}
