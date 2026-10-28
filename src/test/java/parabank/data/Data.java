package parabank.data;

import org.testng.annotations.DataProvider;

import parabank.utils.DataUtils;
import parabank.utils.ExcelUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Data {
	/*** VARIABLES ***/
    private static final String BASE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data";

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
    
    private Object[][] getRegisterDataById(String testCaseId) {
        Object[][] excelData = data(BASE_PATH + File.separator + "registerData" + File.separator + "registerData.xlsx", "Data");

        List<Object[]> registerData = new ArrayList<>();
        for (Object[] row : excelData) {
            if (!testCaseId.equalsIgnoreCase((String) row[0])) {
                continue;
            }
            
            String username = (String) row[9];

            if ("AUTO".equalsIgnoreCase(username)) {
                username = DataUtils.resolveUsername(username);
            }

            RegisterData dataCase = new RegisterData((String) row[0], (String) row[1], (String) row[2], (String) row[3], (String) row[4], (String) row[5], (String) row[6], (String) row[7], (String) row[8], username, (String) row[10], (String) row[11], (String) row[12]);

            registerData.add(new Object[] {dataCase});
        }

        if (registerData.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron datos para el caso de prueba: " + testCaseId);
        }

        return registerData.toArray(new Object[0][]);
    }
    
    private Object[][] getLogInDataById(String testCaseId) {
        Object[][] excelData = data(BASE_PATH + File.separator + "logInData" + File.separator + "logInData.xlsx", "Data");

        List<Object[]> logInData = new ArrayList<>();
        for (Object[] row : excelData) {
            if (!testCaseId.equalsIgnoreCase((String) row[0])) {
                continue;
            }
            
            LogInData dataCase = new LogInData((String) row[0], (String) row[1], (String) row[2],  (String) row[3]);

            logInData.add(new Object[] {dataCase});
        }

        if (logInData.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron datos para el caso de prueba: " + testCaseId);
        }

        return logInData.toArray(new Object[0][]);
    }
    
    // Register Cases
    @DataProvider(name = "Register")
    public Object[][] getRegisterData() {
        return getRegisterDataById("CP01");
    }
    
    @DataProvider(name = "RegisterExistingUser")
    public Object[][] getRegisterExistingUser() {
        return getRegisterDataById("CP02");
    }
    
    @DataProvider(name = "RegisterEmptyFields")
    public Object[][] getRegisterEmptyFields() {
        List<Object[]> registerData = new ArrayList<>();

        for (int i = 3; i <= 14; i++) {
            Object[][] testCaseData = getRegisterDataById(String.format("CP%02d", i));

            for (Object[] row : testCaseData) {
                registerData.add(row);
            }
        }

        return registerData.toArray(new Object[0][]);
    }
    
    @DataProvider(name = "RegisterPasswordsMismatch")
    public Object[][] getRegisterPasswordsMismatchData() {
        return getRegisterDataById("CP15");
    }
    
    // Login Cases
    @DataProvider(name = "LogIn")
    public Object[][] getLogInData() {
        return getLogInDataById("CP01");
    }
    
    @DataProvider(name = "LogInInvalidUsernameOrPassword")
    public Object[][] getLogInInvalidUsernameOrPasswordData() {
    	List<Object[]> logInData = new ArrayList<>();

        for (int i = 2; i <= 3; i++) {
            Object[][] testCaseData = getLogInDataById(String.format("CP%02d", i));

            for (Object[] row : testCaseData) {
                logInData.add(row);
            }
        }

        return logInData.toArray(new Object[0][]);
    }
    
    @DataProvider(name = "LogInEmptyFields")
    public Object[][] getLogInEmptyFieldsData() {
        List<Object[]> logInData = new ArrayList<>();

        for (int i = 4; i <= 6; i++) {
            Object[][] testCaseData = getLogInDataById(String.format("CP%02d", i));

            for (Object[] row : testCaseData) {
            	logInData.add(row);
            }
        }

        return logInData.toArray(new Object[0][]);
    }
}