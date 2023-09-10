package io.hostilerobot.protag.data.type;

public final class NoneType implements ProtagDataType<Void>{
    public static final NoneType INSTANCE = new NoneType();
    private NoneType() {}
    @Override
    public Void getValue() {
        return null;
    }
}
