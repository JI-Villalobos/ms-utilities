package org.jjv.operators;

import org.jjv.instances.OperatorInstance;
import org.jjv.models.Document;
import org.jjv.models.Operator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DocumentOperator {
    Function<List<Document>, List<Document>> clearDuplicatedData = documentList -> {
        List<Document> cleanedList = new ArrayList<>();
        Set<String> identifiersFounded = new HashSet<>();

        documentList.forEach(document -> {
            if (!identifiersFounded.contains(document.rfc())){
                cleanedList.add(document);
                identifiersFounded.add(document.rfc());
            }
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

    //TODO: get operators from an instance
    private Set<String> setOperators(){
        return OperatorInstance.get();
    }
}
