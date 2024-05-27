package org.jjv.processors;

import org.jjv.instances.OperatorInstance;
import org.jjv.models.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class DocumentProcessor {
    Function<List<Document>, List<Document>> clearDuplicatedData = documentList -> {
        List<Document> cleanedList = new ArrayList<>();
        Set<String> identifiersFound = new HashSet<>();
        
        documentList.forEach(document -> {
            identifiersFound.add(document.rfc());
        });

        identifiersFound.forEach(identifier -> {
            List<Document> filteredList = documentList.stream().filter(document -> document.rfc()
                    .equals(identifier)).toList();
            Double subTotal = filteredList.stream().reduce(0.0, (st, document) -> st + document.subtotal(), Double::sum);
            Double total = filteredList.stream().reduce(0.0, (t, document) -> t + document.total(), Double::sum);
            Document document = filteredList.get(0);

            cleanedList.add(new Document(
                    document.nature(), document.rfc(),
                    document.name(), subTotal, total,
                    document.regime(), Document.setTaxRate(total, subTotal))
            );
        });
        return cleanedList;
    };

    Function<List<Document>, List<Document>> compareWithOperators = documentList -> {
        List<Document> comparedList = new ArrayList<>();
        Set<String> operators = setOperators();
        documentList.forEach(document -> {
            if (!operators.contains(document.rfc()))
                comparedList.add(document);
        });

        return comparedList;
    };

    Function<List<Document>, List<Document>> processDocumentList = clearDuplicatedData.andThen(compareWithOperators);

    public List<Document> generateCleanedDocuments(List<Document> documents){
        return processDocumentList.apply(documents);
    }

    public List<Document> clearDuplicatedOnly(List<Document> documents){
        return clearDuplicatedData.apply(documents);
    }

    private Set<String> setOperators(){
        return OperatorInstance.get();
    }
}
