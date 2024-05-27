package org.jjv.processors;

import org.jjv.instances.AccountInstance;
import org.jjv.instances.EntityInstance;
import org.jjv.models.Account;
import org.jjv.models.ClientEntity;
import org.jjv.models.ExtendedProviderEntity;
import org.jjv.models.ProviderEntity;
import org.jjv.mappers.ClientEntityMapper;
import org.jjv.mappers.ExtendedProviderEntityMapper;
import org.jjv.processors.Filter;
import org.jjv.mappers.ProviderEntityMapper;
import org.jjv.utils.DocumentNature;

import java.util.List;

public class EntityProcessor {
   static List<Account> accounts = AccountInstance.get();

    public static void processClientEntities(){
        Filter filter = new Filter();
        ClientEntityMapper mapper = new ClientEntityMapper();
        List<Account> clientAccounts = filter.filter(accounts, DocumentNature.EMITTED);
        List<ClientEntity> clientEntityList = mapper.mapTo(clientAccounts);

        EntityInstance.createClients(clientEntityList);
    }

    public static void processProviderEntities(){
        Filter filter = new Filter();
        ProviderEntityMapper mapper = new ProviderEntityMapper();
        List<Account> providerAccounts = filter.filter(accounts, DocumentNature.RECEIVED);
        List<ProviderEntity> providerEntityList = mapper.mapTo(providerAccounts);

        EntityInstance.createProviders(providerEntityList);
    }

    public static void processExtendedProviderEntities(){
        Filter filter = new Filter();
        ExtendedProviderEntityMapper mapper = new ExtendedProviderEntityMapper();
        List<Account> providerAccounts = filter.filter(accounts, DocumentNature.RECEIVED);
        List<ExtendedProviderEntity> providerEntityList = mapper.mapTo(providerAccounts);

        EntityInstance.createExtendedProviders(providerEntityList);
    }
}
