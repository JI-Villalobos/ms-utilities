package org.jjv.processors;

import org.jjv.models.Account;
import org.jjv.utils.DocumentNature;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Filter {
    BiFunction<List<Account>, DocumentNature, List<Account>> filterByNature =
            (accounts, nature) -> accounts.stream()
                    .filter(document -> document.nature().equals(nature))
                    .collect(Collectors.toList());

    public List<Account> filter(List<Account> accounts, DocumentNature nature){
        return filterByNature.apply(accounts, nature);
    }
}
