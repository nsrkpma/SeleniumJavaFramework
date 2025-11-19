package Kane.SeleniumJavaFramework.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public List<HashMap<String, String>> getExcelData(String excelPath, String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<HashMap<String, String>> excelDataList = new ArrayList<>();

        Row headerRow = sheet.getRow(0);

        int lastRow = sheet.getLastRowNum();
        int lastCol = headerRow.getLastCellNum();

        for (int i = 1; i <= lastRow; i++) {              // loop through all rows
            Row currentRow = sheet.getRow(i);

            HashMap<String, String> dataMap = new HashMap<>();

            for (int j = 0; j < lastCol; j++) {           // for each column
                Cell headerCell = headerRow.getCell(j);
                Cell valueCell = currentRow.getCell(j);

                String key = headerCell.getStringCellValue();
                String value = getCellValue(valueCell);

                dataMap.put(key, value);
            }
            excelDataList.add(dataMap);
        }

        workbook.close();
        fis.close();

        return excelDataList;
    }

    private String getCellValue(Cell cell) {
        if (cell == null)
            return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getStringCellValue();

            case BLANK:
                return "";

            default:
                return "";
        }
    }
}
