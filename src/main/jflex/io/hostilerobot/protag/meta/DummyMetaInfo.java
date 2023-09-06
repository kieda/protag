package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ast.CCommentNode;

import java.util.List;

public class DummyMetaInfo implements ASTMetaInfo{
    private DummyMetaInfo() {

    }
    public static final DummyMetaInfo INSTANCE = new DummyMetaInfo();
    @Override
    public Range getRange() {
        return Range.UNKNOWN;
    }

    @Override
    public List<CCommentNode> getComments() {
        return List.of();
    }
}
