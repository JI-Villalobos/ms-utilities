package org.jjv.instances;

import org.jjv.models.Client;

public class ClientInstance {
    private static Client client;

    public static void create(Client data){
        client = data;
    }

    public static Client getSingle(){
        return client;
    }
}
