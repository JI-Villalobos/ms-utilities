package org.jjv.operators;

import org.jjv.instances.ClientConfigInstance;
import org.jjv.instances.ClientInstance;
import org.jjv.models.Account;
import org.jjv.models.ClientConfig;
import org.jjv.models.Document;
import org.jjv.utils.DefaultValues;
import org.jjv.utils.DocumentNature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountMapper implements Mapper<Document, Account> {
    @Override
    public List<Account> mapTo(List<Document> documentList) {
        List<Account> accounts = new ArrayList<>();
        documentList.forEach(document -> {
            accounts.add(createAccount(document));
        });
        return accounts;
    }

    private String setMainAccount(DocumentNature nature){
        Optional<ClientConfig> config = ClientConfigInstance.getSingle(ClientInstance.getSingle().id());
        if (config.isEmpty()) {
            return "";
        } else if (nature.equals(DocumentNature.EMITTED)) {
            return config.get().buyerMainAccount();
        } else {
            return config.get().sellerMainAccount();
        }
    }

    private String setCodeSat(DocumentNature nature){
        Optional<ClientConfig> config = ClientConfigInstance.getSingle(ClientInstance.getSingle().id());
        if (config.isEmpty()) {
            return "";
        } else if (nature.equals(DocumentNature.EMITTED)) {
            return config.get().buyerSATIdentifier();
        } else {
            return config.get().sellerSATIdentifier();
        }
    }

    private int setAccountNature(DocumentNature nature){
        if (nature.equals(DocumentNature.EMITTED)){
            return DefaultValues.CLIENT;
        } else {
            return DefaultValues.PROVIDER;
        }
    }
    private Account createAccount(Document document){
        String mainAccount = setMainAccount(document.nature());
        String satCode = setCodeSat(document.nature());
        int accountNature = setAccountNature(document.nature());

        return new Account(
                document.nature(),
                mainAccount,
                0,
                mainAccount.concat("."),
                document.name(),
                document.rfc(),
                document.regime(),
                document.taxRate().toString(),
                DefaultValues.CLIENT_PROVIDER,
                accountNature,
                satCode
        );
    }
}
