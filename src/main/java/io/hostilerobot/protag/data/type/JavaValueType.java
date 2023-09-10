package io.hostilerobot.protag.data.type;

public final class JavaValueType<T> implements ProtagDataType<T> {
    private T javaValue;
    public JavaValueType(T javaValue) {
        this.javaValue = javaValue;
    }

    @Override
    public T getValue() {
        return javaValue;
    }
}
