package org.jjv.instances;

import org.jjv.models.ClientEntity;
import org.jjv.models.ProviderEntity;

import java.util.List;

public class EntityInstance {
    private static List<ClientEntity> clientEntityList;
    private static List<ProviderEntity> providerEntityList;

    public static void createClients(List<ClientEntity> entities){
        clientEntityList = entities;
    }

    public static void createProviders(List<ProviderEntity> entities){
        providerEntityList = entities;
    }

    public static List<ClientEntity> getClientEntities(){
        return clientEntityList;
    }

    public static List<ProviderEntity> getProviderEntities(){
        return providerEntityList;
    }
}
