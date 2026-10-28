package parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parabank.utils.AutomationException;
import parabank.utils.Messages;

public class RegisterPage extends BasePage{
	/*** ELEMENTS ***/
	@FindBy(id = "customer.firstName")
	private WebElement firstNameInput;
	@FindBy(id = "customer.lastName")
	private WebElement lastNameInput;
	@FindBy(id = "customer.address.street")
	private WebElement addressInput;
	@FindBy(id = "customer.address.city")
	private WebElement cityInput;
	@FindBy(id = "customer.address.state")
	private WebElement stateInput;
	@FindBy(id = "customer.address.zipCode")
	private WebElement ZIPCodeInput;
	@FindBy(id = "customer.phoneNumber")
	private WebElement phoneNumberInput;
	@FindBy(id = "customer.ssn")
	private WebElement SSNInput;
	@FindBy(id = "customer.username")
	private WebElement usernameInput;
	@FindBy(id = "customer.password")
	private WebElement passwordInput;
	@FindBy(id = "repeatedPassword")
	private WebElement passwordConfirmInput;
	@FindBy(xpath = "//input[@value='Register']")
	private WebElement registerButton;
	@FindBy(id = "customer.username.errors")
	private WebElement existingUserErrorMessage;
	@FindBy(id = "customer.firstName.errors")
	private WebElement firstNameRequiredErrorMessage;
	@FindBy(id = "customer.lastName.errors")
	private WebElement lastNameRequiredErrorMessage;
	@FindBy(id = "customer.address.street.errors")
	private WebElement addressRequiredErrorMessage;
	@FindBy(id = "customer.address.city.errors")
	private WebElement cityRequiredErrorMessage;
	@FindBy(id = "customer.address.state.errors")
	private WebElement stateRequiredErrorMessage;
	@FindBy(id = "customer.address.zipCode.errors")
	private WebElement ZIPCodeRequiredErrorMessage;
	@FindBy(id = "customer.ssn.errors")
	private WebElement SSNRequiredErrorMessage;
	@FindBy(id = "customer.username.errors")
	private WebElement usernameRequiredErrorMessage;
	@FindBy(id = "customer.password.errors")
	private WebElement passwordRequiredErrorMessage;
	@FindBy(id = "repeatedPassword.errors")
	private WebElement passwordConfirmRequiredErrorMessage;
	@FindBy(id = "repeatedPassword.errors")
	private WebElement passwordMismatchErrorMessage;
	
	private static final String FIRST_NAME_INPUT_NAME = "First Name Input";
	private static final String LAST_NAME_INPUT_NAME = "Last Name Input";
	private static final String ADDRESS_INPUT_NAME = "Address Input";
	private static final String CITY_INPUT_NAME = "City Input";
	private static final String STATE_INPUT_NAME = "State Input";
	private static final String ZIP_CODE_INPUT_NAME = "ZIP Code Input";
	private static final String PHONE_NUMBER_INPUT_NAME = "Phone Number Input";
	private static final String SSN_INPUT_NAME = "SSN Input";
	private static final String USERNAME_INPUT_NAME = "Username Input";
	private static final String PASSWORD_INPUT_NAME = "Password Input";
	private static final String PASSWORD_CONFIRM_INPUT_NAME = "Password Confirm Input";
	private static final String REGISTER_BUTTON_NAME = "Register Button";
	private static final String EXISTING_USER_ERROR_MESSAGE_NAME = "Existing User Error Message";
	private static final String FIRST_NAME_REQUIRED_ERROR_MESSAGE_NAME = "First Name Required Error Message";
	private static final String LAST_NAME_REQUIRED_ERROR_MESSAGE_NAME = "Last Name Required Error Message";
	private static final String ADDRESS_REQUIRED_ERROR_MESSAGE_NAME = "Address Required Error Message";
	private static final String CITY_REQUIRED_ERROR_MESSAGE_NAME = "City Required Error Message";
	private static final String STATE_REQUIRED_ERROR_MESSAGE_NAME = "State Required Error Message";
	private static final String ZIP_CODE_REQUIRED_ERROR_MESSAGE_NAME = "Zip Code Required Error Message";
	private static final String SSN_REQUIRED_ERROR_MESSAGE_NAME = "SSN Required Error Message";
	private static final String USERNAME_REQUIRED_ERROR_MESSAGE_NAME = "Username Required Error Message";
	private static final String PASSWORD_REQUIRED_ERROR_MESSAGE_NAME = "Password Required Error Message";
	private static final String PASSWORD_CONFIRM_REQUIRED_ERROR_MESSAGE_NAME = "Password Confirm Required Error Message";
	private static final String PASSWORD_MISMATCH_ERROR_MESSAGE_NAME = "Password Mismatch Error Message";
	
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterPage.class);
	
	/*** CONSTRUCTOR ***/
	public RegisterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*** METHODS ***/
	// Actions
	public void typeFirstName(String firstName) {
		type(firstName, firstNameInput, FIRST_NAME_INPUT_NAME);
	}
	
	public void typeLastName(String lastName) {
		type(lastName, lastNameInput, LAST_NAME_INPUT_NAME);
	}
	
	public void typeAddress(String address) {
		type(address, addressInput, ADDRESS_INPUT_NAME);
	}
	
	public void typeCity(String city) {
		type(city, cityInput, CITY_INPUT_NAME);
	}
	
	public void typeState(String state) {
		type(state, stateInput, STATE_INPUT_NAME);
	}
	
	public void typeZIPCode(String ZIPCode) {
		type(ZIPCode, ZIPCodeInput, ZIP_CODE_INPUT_NAME);
	}
	
	public void typePhoneNumber(String phoneNumber) {
		type(phoneNumber, ZIPCodeInput, PHONE_NUMBER_INPUT_NAME);
	}
	
	public void typeSSN(String SSN) {
		type(SSN, SSNInput, SSN_INPUT_NAME);
	}
	
	public void typeUsername(String username) {
		type(username, usernameInput, USERNAME_INPUT_NAME);
	}
	
	public void typePassword(String password) {
		type(password, passwordInput, PASSWORD_INPUT_NAME);
	}
	
	public void typePasswordConfirm(String passwordConfirm) {
		type(passwordConfirm, passwordConfirmInput, PASSWORD_CONFIRM_INPUT_NAME);
	}
	
	public void clickRegisterButton() {
		click(registerButton, REGISTER_BUTTON_NAME);
	}
	
	public void register(String firstName, String lastName, String address, String city, String state, String ZIPCode, String phoneNumber, String SSN, String username, String password, String passwordConfirm) {
		type(firstName, firstNameInput, FIRST_NAME_INPUT_NAME);
		type(lastName, lastNameInput, LAST_NAME_INPUT_NAME);
		type(address, addressInput, ADDRESS_INPUT_NAME);
		type(city, cityInput, CITY_INPUT_NAME);
		type(state, stateInput, STATE_INPUT_NAME);
		type(ZIPCode, ZIPCodeInput, ZIP_CODE_INPUT_NAME);
		type(phoneNumber, phoneNumberInput, PHONE_NUMBER_INPUT_NAME);
		type(SSN, SSNInput, SSN_INPUT_NAME);
		type(username, usernameInput, USERNAME_INPUT_NAME);
		type(password, passwordInput, PASSWORD_INPUT_NAME);
		type(passwordConfirm, passwordConfirmInput, PASSWORD_CONFIRM_INPUT_NAME);
		click(registerButton, REGISTER_BUTTON_NAME);
		LOGGER.info(Messages.REGISTER_COMPLETED.getMessage());
	}
	
	// Getter
	public String getExistingUserErrorMessageText() {
		String text = getText(existingUserErrorMessage, EXISTING_USER_ERROR_MESSAGE_NAME);
		
		return text;
	}
	
	public String getFirstNameRequiredErrorMessageText() {
		String text = getText(firstNameRequiredErrorMessage, FIRST_NAME_REQUIRED_ERROR_MESSAGE_NAME);
		
		return text;
	}
	
	public String getLastNameRequiredErrorMessageText() {
	    String text = getText(lastNameRequiredErrorMessage, LAST_NAME_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}

	public String getAddressRequiredErrorMessageText() {
	    String text = getText(addressRequiredErrorMessage, ADDRESS_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}

	public String getCityRequiredErrorMessageText() {
	    String text = getText(cityRequiredErrorMessage, CITY_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}

	public String getStateRequiredErrorMessageText() {
	    String text = getText(stateRequiredErrorMessage, STATE_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}

	public String getZIPCodeRequiredErrorMessageText() {
	    String text = getText(ZIPCodeRequiredErrorMessage, ZIP_CODE_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}

	public String getSSNRequiredErrorMessageText() {
	    String text = getText(SSNRequiredErrorMessage, SSN_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}

	public String getUsernameRequiredErrorMessageText() {
	    String text = getText(usernameRequiredErrorMessage, USERNAME_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}

	public String getPasswordRequiredErrorMessageText() {
	    String text = getText(passwordRequiredErrorMessage, PASSWORD_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}

	public String getPasswordConfirmRequiredErrorMessageText() {
	    String text = getText(passwordConfirmRequiredErrorMessage, PASSWORD_CONFIRM_REQUIRED_ERROR_MESSAGE_NAME);
	    return text;
	}
	
	public String getPasswordsMismatchErrorMessageText() {
	    String text = getText(passwordMismatchErrorMessage, PASSWORD_MISMATCH_ERROR_MESSAGE_NAME);
	    return text;
	}
	
	// Validate	
	public boolean isExistingUserErrorMessageVisible() {		
		try {
			elementVisible(existingUserErrorMessage, EXISTING_USER_ERROR_MESSAGE_NAME);
	        return true;
		} catch (AutomationException e) {
			return false;
		}
	}
	
	public boolean isFirstNameRequiredErrorMessageVisible() {		
		try {
			elementVisible(firstNameRequiredErrorMessage, FIRST_NAME_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
		} catch (AutomationException e) {
			return false;
		}
	}
	
	public boolean isLastNameRequiredErrorMessageVisible() {
	    try {
	        elementVisible(lastNameRequiredErrorMessage, LAST_NAME_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isAddressRequiredErrorMessageVisible() {
	    try {
	        elementVisible(addressRequiredErrorMessage, ADDRESS_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isCityRequiredErrorMessageVisible() {
	    try {
	        elementVisible(cityRequiredErrorMessage, CITY_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isStateRequiredErrorMessageVisible() {
	    try {
	        elementVisible(stateRequiredErrorMessage, STATE_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isZIPCodeRequiredErrorMessageVisible() {
	    try {
	        elementVisible(ZIPCodeRequiredErrorMessage, ZIP_CODE_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isSSNRequiredErrorMessageVisible() {
	    try {
	        elementVisible(SSNRequiredErrorMessage, SSN_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isUsernameRequiredErrorMessageVisible() {
	    try {
	        elementVisible(usernameRequiredErrorMessage, USERNAME_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isPasswordRequiredErrorMessageVisible() {
	    try {
	        elementVisible(passwordRequiredErrorMessage, PASSWORD_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isPasswordConfirmRequiredErrorMessageVisible() {
	    try {
	        elementVisible(passwordConfirmRequiredErrorMessage, PASSWORD_CONFIRM_REQUIRED_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}
	
	public boolean isPasswordsMismatchErrorMessageVisible() {
	    try {
	        elementVisible(passwordMismatchErrorMessage, PASSWORD_MISMATCH_ERROR_MESSAGE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}
}
