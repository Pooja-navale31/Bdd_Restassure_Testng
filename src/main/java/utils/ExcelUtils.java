package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<String[]> getCellData(String sheetName) {
        List<String[]> data = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File("src/test/resources/testdata.xlsx"));
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rowCount; i++) { // start from 1 (skip header)
                Row row = sheet.getRow(i);
                String[] rowData = new String[row.getPhysicalNumberOfCells()];
                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    rowData[j] = row.getCell(j).toString();
                }
                data.add(rowData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
