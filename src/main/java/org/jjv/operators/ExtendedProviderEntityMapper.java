package org.jjv.operators;

import org.jjv.instances.ClientConfigInstance;
import org.jjv.instances.ClientInstance;
import org.jjv.models.Account;
import org.jjv.models.ClientConfig;
import org.jjv.models.ExtendedProviderEntity;
import org.jjv.models.ProviderEntity;
import org.jjv.utils.CountryCode;
import org.jjv.utils.DefaultValues;

import java.util.ArrayList;
import java.util.List;

public class ExtendedProviderEntityMapper implements Mapper<Account, ExtendedProviderEntity> {
    Integer client = ClientInstance.getSingle().id();
    ClientConfig config = ClientConfigInstance.getSingle(client).get();
    @Override
    public List<ExtendedProviderEntity> mapTo(List<Account> accounts) {
        List<ExtendedProviderEntity> providerEntityList = new ArrayList<>();
        accounts.forEach(account -> {
            providerEntityList.add(createExtendedProviderEntity(account));
        });

        return providerEntityList;
    }

    private ExtendedProviderEntity createExtendedProviderEntity(Account account){
        return new ExtendedProviderEntity(
                account.name(), account.rfc(), ProviderEntity.setEntityNature(account.rfc()),
                true, false, false,
                account.regime(), DefaultValues.PROVIDER_DESCRIPTION, account.subAccount(),
                applyEntityAccount(account.total()), "D", config.buyerSATIdentifier(),
                ProviderEntity.computeTaxKey(account.taxRate()), DefaultValues.NATIONAL,
                CountryCode.MX, DefaultValues.OTHER_SERVICES
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
