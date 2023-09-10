package io.hostilerobot.protag.data.type;

import io.hostilerobot.protag.data.plugin.ProtagEnvironment;

public final class ProtagEnvironmentType implements ProtagDataType<ProtagEnvironment> {
    private ProtagEnvironment environmentValue;
    public ProtagEnvironmentType(ProtagEnvironment environmentValue) {
        this.environmentValue = environmentValue;
    }

    @Override
    public ProtagEnvironment getValue() {
        return environmentValue;
    }
}
