package org.jjv.models;

import org.jjv.utils.DefaultValues;
import org.jjv.utils.EntityNature;

public class ClientEntity extends Entity{
    private String clientAccount;
    private String incomeAccount;
    private String policyType;

    public ClientEntity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient, boolean isContractor, String regime, String description) {
        super(name, rfc, nature, isProvider, isClient, isContractor, regime, description);
    }

    public ClientEntity(String name, String rfc, EntityNature nature, boolean isProvider, boolean isClient, boolean isContractor, String regime, String description, String clientAccount, String incomeAccount, String policyType) {
        super(name, rfc, nature, isProvider, isClient, isContractor, regime, description);
        this.clientAccount = clientAccount;
        this.incomeAccount = incomeAccount;
        this.policyType = policyType;
    }

    public String getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(String clientAccount) {
        this.clientAccount = clientAccount;
    }

    public String getIncomeAccount() {
        return incomeAccount;
    }

    public void setIncomeAccount(String incomeAccount) {
        this.incomeAccount = incomeAccount;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public static String computeRegime(String rfc){
        if (setEntityNature(rfc).equals(EntityNature.F)){
            return DefaultValues.DEFAULT_F_CODE_REGIME;
        } else if (setEntityNature(rfc).equals(EntityNature.M)) {
            return DefaultValues.DEFAULT_M_CODE_REGIME;
        } else {
            return "";
        }
    }
}
