package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ProtagSymbol;
import io.hostilerobot.protag.lang.ast.CCommentNode;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ProtagMetaTable implements ASTMetaTable<ProtagMetaInfo, ProtagMetaTable.ProtagMetaBuilder> {
    private Map<ProtagNode, ProtagMetaBuilder> builderEntries = new HashMap<>();
    private Map<ProtagNode, ProtagMetaInfo> nodeEntries = new HashMap<>();
    private Map<ProtagSymbol, ProtagMetaInfo> symbolEntries = new HashMap<>();

    @Override
    public ProtagMetaBuilder createBuilder(ProtagNode node) {
        if(builderEntries.containsKey(node) || nodeEntries.containsKey(node)) {
            throw new IllegalStateException("Cannot create another builder for " + node);
        }
        var result = new ProtagMetaBuilder(node);
        builderEntries.put(node, result);
        return result;
    }

    @Override
    public ProtagMetaBuilder getBuilder(ProtagNode node) {
        if(nodeEntries.containsKey(node)) {
            throw new IllegalStateException("Node " + node + " already built");
        }

        return builderEntries.get(node);
    }

    @Override
    public ProtagMetaInfo get(ProtagNode node) {
        return nodeEntries.get(node);
    }

    @Override
    public ProtagMetaInfo get(ProtagSymbol node) {
        return symbolEntries.get(node);
    }

    public final class ProtagMetaBuilder implements ASTMetaInfoBuilder<ProtagMetaInfo> {
        private boolean isBuilt = false;
        private ProtagNode target;
        private Deque<Object> meta = new LinkedList<>();
        private ProtagMetaBuilder(ProtagNode target) {
            this.target = target;
        }
        @Override
        public void addComment(CCommentNode node) {
            meta.add(node);
        }

        @Override
        public void addSep(ProtagSymbol symbol) {
            meta.add(symbol);
        }

        @Override
        public ProtagMetaInfo build(ProtagSymbol symbol) {
            var result = new ProtagMetaInfo(symbol);
            symbolEntries.put(symbol, result);
            nodeEntries.put(target, result);
            builderEntries.remove(target);
            isBuilt = true;
            return result;
        }

        @Override
        public boolean isBuilt() {
            return isBuilt;
        }

        @Override
        public String toString() {
            return "ProtagMetaBuilder{" +
                    "isBuilt=" + isBuilt +
                    ", target=" + target +
                    ", meta=" + meta +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProtagMetaTable{" +
                "builderEntries=" + builderEntries +
                ", nodeEntries=" + nodeEntries +
                ", symbolEntries=" + symbolEntries +
                '}';
    }
}
