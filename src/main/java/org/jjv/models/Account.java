package org.jjv.models;

import org.jjv.utils.DocumentNature;

public record Account(
        DocumentNature nature,
        String mainAccount,
        int identifier,
        String subAccount,
        String name,
        String rfc,
        String regime,
        String taxRate,
        int accountType,
        int accountNature,
        String satCode
) {
}
