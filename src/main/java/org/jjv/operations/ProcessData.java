package org.jjv.operations;

import org.jjv.instances.AccountInstance;
import org.jjv.instances.DocumentInstance;
import org.jjv.instances.OperatorInstance;
import org.jjv.models.Account;
import org.jjv.models.Document;
import org.jjv.operators.AccountMapper;
import org.jjv.operators.DocumentOperator;
import org.jjv.operators.Mapper;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public class ProcessData {
   private static List<Document> processDocuments() throws IOException {
       List<Document> documents = ExtractOperation.extractDocuments();
       DocumentOperator documentOperator = new DocumentOperator();
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
