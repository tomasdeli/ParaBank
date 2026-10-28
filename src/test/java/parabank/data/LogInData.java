package parabank.data;

public class LogInData {
	/*** VARIABLES ***/
    private String testCase;
    private String username;
    private String password;
    private String expectedMessage;

    /*** CONSTRUCTOR ***/
    public LogInData(String testCase, String firstName, String lastName, String expectedMessage) {
        this.testCase = testCase;
        this.username = firstName;
        this.password = lastName;
        this.expectedMessage = expectedMessage;
    }
    
    /*** GETTER ***/
    public String getTestCase() {
        return testCase;
    }
    
    public String getUsername() {
    	return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getExpectedMessage() {
    	return expectedMessage;
    }
}