package org.jjv.models;

public record ClientConfig(
        Integer id,
        Integer clientId,
        Integer organization,
        String sellerMainAccount,
        String sellerSATIdentifier,
        String buyerMainAccount,
        String buyerSATIdentifier,
        String expenseMainAccount,
        Double minimumAmountToApply
) {
    public ClientConfig(Integer clientId, Integer organization,
                        String sellerMainAccount, String sellerSATIdentifier,
                        String buyerMainAccount, String buyerSATIdentifier,
                        String expenseMainAccount, Double minimumAmountToApply) {
        this(null, clientId, organization,
                sellerMainAccount, sellerSATIdentifier,
                buyerMainAccount, buyerSATIdentifier,
                expenseMainAccount, minimumAmountToApply
        );
    }
}
