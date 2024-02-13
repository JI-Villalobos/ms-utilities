package org.jjv.operators;

import org.jjv.instances.ClientConfigInstance;
import org.jjv.instances.ClientInstance;
import org.jjv.models.Account;
import org.jjv.models.ClientConfig;
import org.jjv.models.ClientEntity;
import org.jjv.utils.DefaultValues;

import java.util.ArrayList;
import java.util.List;
public class ClientEntityMapper implements Mapper<Account, ClientEntity> {
    Integer client = ClientInstance.getSingle().id();
    ClientConfig config = ClientConfigInstance.getSingle(client).get();

    @Override
    public List<ClientEntity> mapTo(List<Account> accounts) {
        List<ClientEntity> clientEntityList = new ArrayList<>();
        accounts.forEach(account -> {
            clientEntityList.add(createClientEntity(account));
        });
        return clientEntityList;
    }

    private ClientEntity createClientEntity(Account account) {
        return new ClientEntity(
                account.name(), account.rfc(),
                ClientEntity.setEntityNature(account.rfc()),
                false, true, false,
                ClientEntity.computeRegime(account.rfc()), DefaultValues.CLIENT_DESCRIPTION, account.subAccount(),
                config.incomeMainAccount(), "D"
        );
    }

}
