package org.jjv.models;

import org.jjv.models.Entity;
import org.jjv.utils.EntityNature;

public class ProviderEntity extends Entity {
    private String providerAccount;
    private String expenseAccount;
    private String policyType;
    private String diot;
    private String taxKey;

    public ProviderEntity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient, boolean isContractor, String regime, String description) {
        super(name, rfc, nature, isProvider, isClient, isContractor, regime, description);
    }

    public ProviderEntity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient, boolean isContractor, String regime, String description, String providerAccount, String expenseAccount, String policyType, String diot, String taxKey) {
        super(name, rfc, nature, isProvider, isClient, isContractor, regime, description);
        this.providerAccount = providerAccount;
        this.expenseAccount = expenseAccount;
        this.policyType = policyType;
        this.diot = diot;
        this.taxKey = taxKey;
    }

    public String getProviderAccount() {
        return providerAccount;
    }

    public void setProviderAccount(String providerAccount) {
        this.providerAccount = providerAccount;
    }

    public String getExpenseAccount() {
        return expenseAccount;
    }

    public void setExpenseAccount(String expenseAccount) {
        this.expenseAccount = expenseAccount;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getDiot() {
        return diot;
    }

    public void setDiot(String diot) {
        this.diot = diot;
    }

    public String getTaxKey() {
        return taxKey;
    }

    public void setTaxKey(String taxKey) {
        this.taxKey = taxKey;
    }
}
