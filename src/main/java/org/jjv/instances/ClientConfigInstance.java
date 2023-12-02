package org.jjv.instances;

import org.jjv.models.ClientConfig;
import org.jjv.utils.DocumentNature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClientConfigInstance {
    private static List<ClientConfig> configList;
    private static Map<DocumentNature, Integer> tempConfig;

    public static void create(List<ClientConfig> list){
        configList = list;
    }

    public static void create(Map<DocumentNature, Integer> config){
        tempConfig = config;
    }
    public static Map<DocumentNature, Integer> getTempConfig(){
        return tempConfig;
    }
    public static List<ClientConfig> get(){
        return configList;
    }

    public static Optional<ClientConfig> getSingle(Integer clientId){
        for (ClientConfig clientConfig : configList){
            if (clientConfig.clientId().equals(clientId))
                return Optional.of(clientConfig);
        }

        return Optional.empty();
    }
}
