package parabank.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	/*** VARIABLES ***/
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static DataFormatter formatter = new DataFormatter();

	/*** METHODS ***/
	// Excel configuration
	public ExcelUtils(String path, String sheet) {
		try {
			workbook = new XSSFWorkbook(path);

			ExcelUtils.sheet = workbook.getSheet(sheet);
		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("Error Cause: " + e.getCause());
			e.printStackTrace();
		}
	}

	public static int getRowCount() {
		int rowCount = 0;

		try {
			rowCount = sheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("Error Cause: " + e.getCause());
			e.printStackTrace();
		}

		return rowCount;
	}

	// Getter
	public static int getColCount() {
		int colCount = 0;

		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			
		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("Error Cause: " + e.getCause());
			e.printStackTrace();
		}

		return colCount;
	}

	public static String getCellData(int rowNum, int colNum) {
		String cellData = null;

		try {
			cellData = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));

		} catch (Exception e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("Error Cause: " + e.getCause());
			e.printStackTrace();
		}

		return cellData;
	}
}