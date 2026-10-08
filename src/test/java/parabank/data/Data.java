package parabank.data;

import org.testng.annotations.DataProvider;

import parabank.utils.DataUtils;
import parabank.utils.ExcelUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Data {
	/*** VARIABLES ***/
    private static final String BASE_PATH = System.getProperty("user.dir") 
        + File.separator + "src" 
        + File.separator + "test" 
        + File.separator + "resources" 
        + File.separator + "data";

    /*** METHODS ***/
    // Read Excel
    public Object[][] data(String path, String sheet) {
        new ExcelUtils(path, sheet);

        int row_count = ExcelUtils.getRowCount();
        int col_count = ExcelUtils.getColCount();

        List<Object[]> rows = new ArrayList<>();
        
        for (int i = 1; i < row_count; i++) {
        	boolean rowIsEmpty = true;
        	Object[] dt = new Object[col_count];
        	
            for (int j = 0; j < col_count; j++) {
                String cellData = ExcelUtils.getCellData(i, j);
                
                if (cellData != null && !cellData.trim().isEmpty()) {
                	rowIsEmpty = false;
                }
                
                dt[j] = cellData;
            }
            
            if (!rowIsEmpty) {
            	rows.add(dt);
            }
        }
        
        return rows.toArray(new Object[0][]);
    }
    
    // Register Cases
    @DataProvider(name = "RegisterData")
    public Object[][] getRegisterData() {
    	Object[][] excelData = data(BASE_PATH + File.separator + "registerData" + File.separator + "registerData.xlsx", "Data");

        List<Object[]> registerData = new ArrayList<>();

        for (Object[] row : excelData) {
        	
        	String username = DataUtils.resolveUsername((String) row[9]);
        	
            RegisterData dataCase = new RegisterData((String) row[0], (String) row[1], (String) row[2], (String) row[3], (String) row[4], (String) row[5], (String) row[6], (String) row[7], (String) row[8], username, (String) row[10], (String) row[11]);

            registerData.add(new Object[] {dataCase});
        }

        return registerData.toArray(new Object[0][]);
    }
}