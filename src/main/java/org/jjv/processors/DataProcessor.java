package org.jjv.processors;

import org.jjv.instances.AccountInstance;
import org.jjv.instances.OperatorInstance;
import org.jjv.models.Account;
import org.jjv.models.Document;
import org.jjv.mappers.AccountMapper;
import org.jjv.operations.ExtractOperation;

import java.io.IOException;
import java.util.List;
import java.util.Set;


public class DataProcessor {
   private static List<Document> processDocuments() throws IOException {
       List<Document> documents = ExtractOperation.extractDocuments();
       DocumentProcessor documentOperator = new DocumentProcessor();
       Set<String> operators = OperatorInstance.get();

       if (operators.isEmpty())
           return documentOperator.clearDuplicatedOnly(documents);
       return documentOperator.generateCleanedDocuments(documents);
   }

   public static void processAccounts() throws IOException {
       AccountMapper mapper = new AccountMapper();
       List<Account> accounts = mapper.mapTo(processDocuments());
       AccountInstance.create(accounts);
   }
}
