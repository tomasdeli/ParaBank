
package parabank.utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils implements AutoCloseable {

    /*** VARIABLES ***/
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final DataFormatter formatter = new DataFormatter();

    /*** CONSTRUCTOR ***/
    public ExcelUtils(String path, String sheetName) {
        try {
            workbook = new XSSFWorkbook(path);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException("No existe la hoja de Excel: " + sheetName);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al abrir el archivo Excel: " + path, e);
        }
    }

    /*** METHODS ***/
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public int getColCount() {
        if (sheet.getRow(0) == null) {
            return 0;
        }

        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    public String getCellData(int rowNum, int colNum) {
        if (sheet.getRow(rowNum) == null || sheet.getRow(rowNum).getCell(colNum) == null) {
            return "";
        }

        return formatter.formatCellValue(
            sheet.getRow(rowNum).getCell(colNum)
        );
    }

    @Override
    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
            workbook = null;
            sheet = null;
        }
    }
}
