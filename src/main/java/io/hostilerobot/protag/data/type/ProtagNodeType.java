package io.hostilerobot.protag.data.type;

import io.hostilerobot.protag.data.node.ProtagNode;

public final class ProtagNodeType<T extends ProtagNode> implements ProtagDataType<T> {
    private T nodeValue;
    public ProtagNodeType(T nodeValue) {
        this.nodeValue = nodeValue;
    }

    @Override
    public T getValue() {
        return nodeValue;
    }
}
