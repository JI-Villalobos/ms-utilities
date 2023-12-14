package org.jjv.generators;

import org.jjv.instances.AccountInstance;
import org.jjv.instances.PathInstance;
import org.jjv.models.Account;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Generator{
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
}
