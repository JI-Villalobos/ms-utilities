package org.jjv.models;

import org.jjv.utils.DocumentNature;
import org.jjv.utils.TaxRate;

public record Document(
        DocumentNature nature,
        String rfc,
        String name,
        Double subtotal,
        Double total,
        String regime,
        TaxRate taxRate) {

    public static TaxRate setTaxRate(double total, double subtotal){
        if (total == subtotal){
            return TaxRate.TAX_0;
        }
        else {
            return TaxRate.TAX_16;
        }
    }

    public static DocumentNature setNature(String nature){
        if (nature.equals("Emitido")){
            return DocumentNature.EMITTED;
        } else if (nature.equals("Recibido")){
            return DocumentNature.RECEIVED;
        } else {
            return DocumentNature.NA;
        }
    }
}
