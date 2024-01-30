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
import java.util.concurrent.atomic.AtomicReference;

public class AccountMapper implements Mapper<Document, Account> {
    @Override
    public List<Account> mapTo(List<Document> documentList) {
        AtomicReference<Integer> sellerAccountIndex = new AtomicReference<>(ClientConfigInstance.getTempConfig().get(DocumentNature.RECEIVED));
        AtomicReference<Integer> buyerAccountIndex = new AtomicReference<>(ClientConfigInstance.getTempConfig().get(DocumentNature.EMITTED));
        List<Account> accounts = new ArrayList<>();
        documentList.forEach(document -> {
            if (document.nature().equals(DocumentNature.EMITTED)){
                accounts.add(createAccount(document, buyerAccountIndex.get()));
                buyerAccountIndex.getAndSet(buyerAccountIndex.get() + 1);
            } else {
                accounts.add(createAccount(document, sellerAccountIndex.get()));
                sellerAccountIndex.getAndSet(sellerAccountIndex.get() + 1);
            }
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

    private static String deleteFloatChars(String regime){
        return regime.substring(0, 3);
    }
    private Account createAccount(Document document, Integer index){
        String mainAccount = setMainAccount(document.nature());
        String satCode = setCodeSat(document.nature());
        int accountNature = setAccountNature(document.nature());

        return new Account(
                document.nature(),
                mainAccount,
                index,
                mainAccount.concat(".").concat(Integer.toString(index)),
                document.name(),
                document.rfc(),
                deleteFloatChars(document.regime()),
                document.taxRate().toString(),
                DefaultValues.CLIENT_PROVIDER,
                accountNature,
                satCode,
                document.total()
        );
    }
}
