package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ProtagSymbol;
import io.hostilerobot.protag.lang.ast.CCommentNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DummyMetaInfo implements ASTMetaInfo{
    private DummyMetaInfo() {

    }
    public static final DummyMetaInfo INSTANCE = new DummyMetaInfo();
    private LinkedList<CCommentNode>[] comments = new LinkedList[0];

    @Override
    public ProtagSymbol getSymbol() {
        return null;
    }

    @Override
    public Range getRange() {
        return Range.UNKNOWN;
    }


    @Override
    public Deque<CCommentNode>[] getComments() {
        return comments;
    }
}
