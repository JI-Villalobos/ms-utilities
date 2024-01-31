package org.jjv.generators;

import org.jjv.instances.AccountInstance;
import org.jjv.instances.EntityInstance;
import org.jjv.instances.PathInstance;
import org.jjv.models.Account;
import org.jjv.models.ClientEntity;
import org.jjv.models.ExtendedProviderEntity;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Generator{
    static String sep = "";
    public static void generateAccountFile() throws IOException {
        List<Account> accounts = AccountInstance.get();
        PrintWriter printWriter = new PrintWriter(PathInstance.getPath(), StandardCharsets.UTF_8);
        accounts.forEach(account -> {
            printWriter.println(
                    account.subAccount() + "," + account.name() + "," + account.accountType() + "," + account.accountNature() + "," + account.satCode()
            );
        });
        printWriter.close();
    }

    public static void generateOperatorsFile() throws IOException{
        List<ClientEntity> clientEntities = EntityInstance.getClientEntities();
        List<ExtendedProviderEntity> providerEntities = EntityInstance.getExtendedProviderEntityList();
        PrintWriter printWriter = new PrintWriter(PathInstance.getPath(), StandardCharsets.UTF_8);
        clientEntities.forEach(client -> {
            printWriter.println(
                    client.getName() + "," + client.getRfc() + "," + client.getNature() + "," + client.getRegime()
                            + "," + sep + "," + client.isProvider() + "," + sep + "," + sep + "," + sep + "," + sep + "," + sep + "," + sep + "," + sep + ","
                            + client.isClient() + "," + client.getClientAccount()  + "," + client.getIncomeAccount() + ","
                            + client.getPolicyType() + "," + client.getDescription() + "," + client.isContractor()
            );
        });
        providerEntities.forEach(provider -> {
            printWriter.println(
                    provider.getName() + "," + provider.getRfc() + "," + provider.getNature() + "," + provider.getRegime()
                            + "," + provider.getNationality() + "," + provider.isProvider() + "," + provider.getProviderAccount()
                            + "," + provider.getExpenseAccount() + "," + provider.getPolicyType() + "," + provider.getDescription()
                            + "," + provider.getCountry() + "," + provider.getDIOTOperation() + provider.getTaxKey()
                            + "," + provider.isClient() + "," + sep + "," + sep + "," + sep + "," + sep + "," + provider.isContractor()

            );
        });
        printWriter.close();
    }
}
