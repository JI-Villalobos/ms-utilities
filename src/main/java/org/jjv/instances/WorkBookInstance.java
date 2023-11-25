package org.jjv.instances;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WorkBookInstance {
    private static Workbook create() throws IOException {
        Workbook workbook = null;
        String path = PathInstance.getPath();
        FileInputStream inputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(inputStream);

        inputStream.close();
        return workbook;
    }

    public static Sheet getSheet() throws IOException {
        Workbook workbook = create();

        return workbook.getSheetAt(0);
    }
}
