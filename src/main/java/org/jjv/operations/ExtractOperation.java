package org.jjv.operations;

import org.apache.poi.ss.usermodel.Sheet;
import org.jjv.instances.WorkBookInstance;
import org.jjv.models.Client;
import org.jjv.models.Operator;
import org.jjv.readers.ClientReader;
import org.jjv.readers.OperatorReader;
import org.jjv.readers.Reader;

import java.io.IOException;
import java.util.List;

public class ExtractOperation {
    public static List<Operator> extractOperators() throws IOException {
        Sheet sheet = WorkBookInstance.getSheet();
        Reader<Operator> operatorReader = new OperatorReader();

        return operatorReader.collectData(sheet);
    }

    public static List<Client> extractClients() throws IOException{
        Sheet sheet = WorkBookInstance.getSheet();
        Reader<Client> clientReader = new ClientReader();

        return clientReader.collectData(sheet);
    }

}
