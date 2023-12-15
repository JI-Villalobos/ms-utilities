package org.jjv.operations;

import org.jjv.instances.AccountInstance;
import org.jjv.instances.EntityInstance;
import org.jjv.models.Account;
import org.jjv.models.ClientEntity;
import org.jjv.models.ProviderEntity;
import org.jjv.operators.ClientEntityMapper;
import org.jjv.operators.Filter;
import org.jjv.operators.ProviderEntityMapper;
import org.jjv.utils.DocumentNature;

import java.util.List;

public class EntityProcessor {
   static List<Account> accounts = AccountInstance.get();

    public static void setClientEntities(){
        Filter filter = new Filter();
        ClientEntityMapper mapper = new ClientEntityMapper();
        List<Account> clientAccounts = filter.filter(accounts, DocumentNature.EMITTED);
        List<ClientEntity> clientEntityList = mapper.mapTo(clientAccounts);

        EntityInstance.createClients(clientEntityList);
    }

    public static void setProviderEntities(){
        Filter filter = new Filter();
        ProviderEntityMapper mapper = new ProviderEntityMapper();
        List<Account> providerAccounts = filter.filter(accounts, DocumentNature.RECEIVED);
        List<ProviderEntity> providerEntityList = mapper.mapTo(providerAccounts);

        EntityInstance.createProviders(providerEntityList);
    }
}
