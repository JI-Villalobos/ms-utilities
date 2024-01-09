package org.jjv.models;

import org.jjv.utils.CountryCode;
import org.jjv.utils.EntityNature;

public class ExtendedProviderEntity extends ProviderEntity{
    private String nationality;
    private CountryCode country;
    private String DIOTOperation;
    public ExtendedProviderEntity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient, boolean isContractor, String regime, String description) {
        super(name, rfc, nature, isProvider, isClient, isContractor, regime, description);
    }

    public ExtendedProviderEntity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient,
                                  boolean isContractor, String regime, String description, String providerAccount,
                                  String expenseAccount, String policyType, String diot, String taxKey) {
        super(name, rfc, nature, isProvider, isClient, isContractor, regime, description, providerAccount,
                expenseAccount, policyType, diot, taxKey);
    }

    public ExtendedProviderEntity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient,
                                  boolean isContractor, String regime, String description, String nationality,
                                  CountryCode country, String DIOTOperation) {
        super(name, rfc, nature, isProvider, isClient, isContractor, regime, description);
        this.nationality = nationality;
        this.country = country;
        this.DIOTOperation = DIOTOperation;
    }

    public ExtendedProviderEntity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient,
                                  boolean isContractor, String regime, String description, String providerAccount,
                                  String expenseAccount, String policyType, String diot, String taxKey,
                                  String nationality, CountryCode country, String DIOTOperation) {
        super(name, rfc, nature, isProvider, isClient, isContractor, regime, description, providerAccount,
                expenseAccount, policyType, diot, taxKey);
        this.nationality = nationality;
        this.country = country;
        this.DIOTOperation = DIOTOperation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }

    public String getDIOTOperation() {
        return DIOTOperation;
    }

    public void setDIOTOperation(String DIOTOperation) {
        this.DIOTOperation = DIOTOperation;
    }
}
