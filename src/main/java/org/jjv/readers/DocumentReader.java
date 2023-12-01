package org.jjv.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jjv.models.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentReader implements Reader<Document> {
    @Override
    public List<Document> collectData(Sheet sheet) {
        List<Document> documents = new ArrayList<>();
        String nature = "";
        String rfc = "";
        String name = "";
        double subtotal = 0;
        double total = 0;
        double regime = 0;
        Row rowIndex = sheet.getRow(1);

        for (int i = rowIndex.getRowNum(); i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < 6; j++) {
                Cell cell = row.getCell(j);
                switch (cell.getColumnIndex()){
                    case 0 -> nature = cell.getStringCellValue();
                    case 1 -> rfc = cell.getStringCellValue();
                    case 2 -> name = cell.getStringCellValue();
                    case 3 -> total = cell.getNumericCellValue();
                    case 4 -> {
                        if (cell.getCellType() == CellType.STRING){
                            regime = Double.parseDouble(cell.getStringCellValue());
                        } else {
                            regime = cell.getNumericCellValue();
                        }
                    }
                    case 5 -> subtotal = cell.getNumericCellValue();
                }
            }
            Document document = new Document(
                    Document.setNature(nature), rfc, name, subtotal, total,
                    Double.toString(regime), Document.setTaxRate(total, subtotal)
            );
            documents.add(document);
        }

        return documents;
    }
}
