package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ProtagSymbol;
import io.hostilerobot.protag.lang.ast.CCommentNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ProtagMetaInfo implements ASTMetaInfo{
    private LinkedList<CCommentNode>[] comments = null;
    private ProtagSymbol symbol;
    public ProtagMetaInfo(ProtagSymbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public ProtagSymbol getSymbol() {
        return symbol;
    }

    @Override
    public Range getRange() {
        return null;
    }

    @Override
    public Deque<CCommentNode>[] getComments() {
        if(comments == null) {
            comments = new LinkedList[0];
        }
        return comments;
    }

    @Override
    public String toString() {
        return "ProtagMetaInfo{" +
                "symbol=" + symbol +
                '}';
    }
}
