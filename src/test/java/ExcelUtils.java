import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelUtils {
    XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public ExcelUtils(String excelPath, String sheetName) throws IOException {
        workbook = new XSSFWorkbook(excelPath);

        sheet = workbook.getSheet(sheetName);
    }

    public static Object getCellData(int rowNum, int colNum) {

        DataFormatter formatter = new DataFormatter();

        Object value = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));

        return value;

    }
}
