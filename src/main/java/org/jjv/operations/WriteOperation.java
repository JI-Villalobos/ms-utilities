package org.jjv.operations;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jjv.instances.ClientInstance;
import org.jjv.instances.PathInstance;
import org.jjv.models.Client;
import org.jjv.persistence.ClientRepository;
import org.jjv.persistence.Repository;
import org.jjv.templates.Headers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class WriteOperation {
    public static void writeClientConfigTemplate() throws IOException, SQLException {
        List<Client> clients = ClientInstance.getClients();
        String[] headers = Headers.ConfigFileHeaders;
        String path = PathInstance.getPath();
        FileOutputStream outputStream = new FileOutputStream(path);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "config");

        Row row;
        Cell cell;

        //set headers
        row = sheet.createRow(0);
        for (int i = 0; i < 11; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }

        Row index = sheet.createRow(1);
        int clientIndex = 0;
        for (int i = index.getRowNum(); i < clients.size(); i++) {
            Row r = sheet.createRow(i);
            Cell clientCell = r.createCell(0);
            clientCell.setCellValue(clients.get(clientIndex).name());
            Cell clientIdCell = r.createCell(1, CellType.NUMERIC);
            clientIdCell.setCellValue(clients.get(clientIndex).id());
            clientIndex++;
        }

        workbook.write(outputStream);
        outputStream.close();
    }
}
