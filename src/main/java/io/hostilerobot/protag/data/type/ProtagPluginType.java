package io.hostilerobot.protag.data.type;

import io.hostilerobot.protag.data.plugin.ProtagPlugin;

public final class ProtagPluginType implements ProtagDataType<ProtagPlugin> {
    private ProtagPlugin pluginValue;
    public ProtagPluginType(ProtagPlugin pluginValue) {
        this.pluginValue = pluginValue;
    }

    @Override
    public ProtagPlugin getValue() {
        return pluginValue;
    }
}
