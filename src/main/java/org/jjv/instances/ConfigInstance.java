package org.jjv.instances;

import org.jjv.utils.ConfigModel;

import java.util.List;

public class ConfigInstance  {
    private static List<ConfigModel> configList;
    private static ConfigModel configModel;

    public static void create(ConfigModel config) {
        configModel = config;
    }
    public static ConfigModel getSingle() {
        return configModel;
    }
}
