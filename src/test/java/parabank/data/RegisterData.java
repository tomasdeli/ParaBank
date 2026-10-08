package parabank.data;

public class RegisterData {
	/*** VARIABLES ***/
    private String testCase;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String ZIPCode;
    private String phoneNumber;
    private String SSN;
    private String username;
    private String password;
    private String passwordConfirm;

    /*** CONSTRUCTOR ***/
    public RegisterData(String testCase, String firstName, String lastName, String address, String city, String state, String ZIPCode, String phoneNumber, String SSN, String username, String password, String passwordConfirm) {
        this.testCase = testCase;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.ZIPCode = ZIPCode;
        this.phoneNumber = phoneNumber;
        this.SSN = SSN;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
    
    /*** GETTER ***/
    public String getTestCase() {
        return testCase;
    }
    
    public String getFirstName() {
    	return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getState() {
        return state;
    }
    
    public String getZIPCode() {
        return ZIPCode;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getSSN() {
        return SSN;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
}