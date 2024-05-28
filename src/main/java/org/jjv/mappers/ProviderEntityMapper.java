package org.jjv.mappers;

import org.jjv.instances.ClientConfigInstance;
import org.jjv.instances.ClientInstance;
import org.jjv.models.Account;
import org.jjv.models.ClientConfig;
import org.jjv.models.ProviderEntity;
import org.jjv.utils.DefaultValues;

import java.util.ArrayList;
import java.util.List;

public class ProviderEntityMapper implements Mapper<Account, ProviderEntity> {
    Integer client = ClientInstance.getSingle().id();
    ClientConfig config = ClientConfigInstance.getSingle(client).get();
    @Override
    public List<ProviderEntity> mapTo(List<Account> accounts) {
        List<ProviderEntity> providerEntityList = new ArrayList<>();
        accounts.forEach(account -> providerEntityList.add(createProviderEntity(account)));
        return providerEntityList;
    }

    private ProviderEntity createProviderEntity(Account account) {
        return new ProviderEntity(
                account.name(), account.rfc(), ProviderEntity.setEntityNature(account.rfc()),
                true, false, false,
                account.regime(), DefaultValues.PROVIDER_DESCRIPTION, account.subAccount(),
                applyEntityAccount(account.total()), "D", config.buyerSATIdentifier(),
                ProviderEntity.computeTaxKey(account.taxRate())
        );
    }

    private String applyEntityAccount(Double total) {
        if (total >= config.minimumAmountToApply()) {
            return config.sellerSATIdentifier();
        } else {
            return "";
        }
    }
}
