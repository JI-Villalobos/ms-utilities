package org.jjv.instances;

import org.jjv.models.ClientConfig;

import java.util.List;
import java.util.Optional;

public class ClientConfigInstance {
    private static List<ClientConfig> configList;
    public static void create(List<ClientConfig> list){
        configList = list;
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
