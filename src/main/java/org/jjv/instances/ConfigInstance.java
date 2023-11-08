package org.jjv.instances;

import org.jjv.utils.Config;
import org.jjv.utils.ConfigModel;

import java.util.List;

public class ConfigInstance implements Instance<ConfigModel> {
    private List<ConfigModel> configList;
    private ConfigModel configModel;

    @Override
    public void create(List<ConfigModel> configGroups) {
        configList = configGroups;
    }

    @Override
    public void create(ConfigModel config) {
        configModel = config;
    }

    @Override
    public List<ConfigModel> get() {
        return configList;
    }

    @Override
    public ConfigModel getSingle() {
        return configModel;
    }
}
