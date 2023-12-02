package org.jjv.operators;

import org.jjv.models.Document;
import org.jjv.utils.DocumentNature;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Filter {
    BiFunction<List<Document>, DocumentNature, List<Document>> filterByNature =
            (documentList, nature) -> documentList.stream()
                    .filter(document -> document.nature().equals(nature))
                    .collect(Collectors.toList());

    public List<Document> filter(List<Document> documents, DocumentNature nature){
        return filterByNature.apply(documents, nature);
    }
}
