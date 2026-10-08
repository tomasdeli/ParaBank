package parabank.data;

public class RegisterData {

    private String testCase;
    private String firstName;

    public RegisterData(String testCase, String firstName) {
        this.testCase = testCase;
        this.firstName = firstName;
    }

    public String getTestCase() {
        return testCase;
    }

    public String getFirstName() {
        return firstName;
    }
}