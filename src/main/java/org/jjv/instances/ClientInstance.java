package org.jjv.instances;

import org.jjv.models.Client;

import java.util.List;

public class ClientInstance {
    private static Client client;
    private static List<Client> clients;

    public static void create(Client data){
        client = data;
    }

    public static void create(List<Client> clientList){
        clients = clientList;
    }
    public static Client getSingle(){
        return client;
    }

    public static List<Client> getClients(){
        return clients;
    }
}
