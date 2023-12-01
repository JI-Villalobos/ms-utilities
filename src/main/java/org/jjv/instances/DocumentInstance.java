package org.jjv.instances;

import org.jjv.models.Document;

import java.util.List;

public class DocumentInstance {
    private static List<Document> documents;

    public static void create(List<Document> documentList){
        documents = documentList;
    }
    public static List<Document> get(){
        return documents;
    }
}
