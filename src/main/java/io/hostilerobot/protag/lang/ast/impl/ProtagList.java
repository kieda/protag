package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagList;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.Deque;
import java.util.List;

public final class ProtagList extends AbstractListLikeNode<ProtagNode> implements IProtagList {
    public ProtagList(Deque<ProtagNode> listItems) {
    }
}
