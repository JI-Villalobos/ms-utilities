package org.jjv.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jjv.instances.ClientInstance;
import org.jjv.models.Operator;

import java.util.ArrayList;
import java.util.List;

public class OperatorReader implements Reader<Operator> {
    @Override
    public List<Operator> collectData(Sheet sheet) {
        List<Operator> operators = new ArrayList<>();
        String name = "";
        String rfc = "";
        Integer clientId = ClientInstance.getSingle().id();
        Row rowIndex = sheet.getRow(1);

        for (int i = rowIndex.getRowNum(); i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < 2; j++) {
                Cell cell = row.getCell(j);
                switch (cell.getColumnIndex()){
                    case 0 -> name = cell.getStringCellValue();
                    case 1 -> rfc = cell.getStringCellValue();
                }
            }
            Operator operator = new Operator(name, rfc, clientId);
            operators.add(operator);
        }
        return operators;
    }
}
