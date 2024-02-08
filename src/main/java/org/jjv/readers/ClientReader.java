package org.jjv.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jjv.models.Client;
import org.jjv.utils.DefaultValues;

import java.util.ArrayList;
import java.util.List;

public class ClientReader implements Reader<Client> {
    @Override
    public List<Client> collectData(Sheet sheet) {
        List<Client> clients = new ArrayList<>();
        Row index = sheet.getRow(1);
        String clientName = "";
        for (int i = index.getRowNum(); i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < 1; j++) {
                Cell cell = row.getCell(j);
                if (cell.getColumnIndex() == 0) {
                    clientName = cell.getStringCellValue();
                }
            }
            Client client = new Client(clientName, DefaultValues.ORGANIZATION_NUMBER);
            clients.add(client);
        }
        return clients;
    }
}
