package org.jjv.instances;

import org.jjv.models.Account;

import java.util.List;

public class AccountInstance {
    private static List<Account> accounts;

    public static void create(List<Account> accountList){
        accounts = accountList;
    }

    public static List<Account> get(){
        return accounts;
    }
}
