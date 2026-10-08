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
	@FindBy(className = "title")
	private WebElement welcomeTitle;
	@FindBy(xpath = "//p[text()='Your account was created successfully. You are now logged in.']")
	private WebElement registrationSuccessMessage;
	
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
	private static final String WELCOME_TITLE_NAME = "Welcome Title";
	private static final String REGISTRATION_SUCCESS_MESSAGE_NAME = "Registration Success Message";
	
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
		
		LOGGER.info(Messages.REGISTER_PAGE_FIRST_NAME_COMPLETED.getMessage(firstName));
	}
	
	public void typeLastName(String lastName) {
		type(lastName, lastNameInput, LAST_NAME_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_LAST_NAME_COMPLETED.getMessage(lastName));
	}
	
	public void typeAddress(String address) {
		type(address, addressInput, ADDRESS_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_ADDRESS_COMPLETED.getMessage(address));
	}
	
	public void typeCity(String city) {
		type(city, cityInput, CITY_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_CITY_COMPLETED.getMessage(city));
	}
	
	public void typeState(String state) {
		type(state, stateInput, STATE_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_STATE_COMPLETED.getMessage(state));
	}
	
	public void typeZIPCode(String ZIPCode) {
		type(ZIPCode, ZIPCodeInput, ZIP_CODE_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_ZIP_CODE_COMPLETED.getMessage(ZIPCode));
	}
	
	public void typePhoneNumber(String phoneNumber) {
		type(phoneNumber, ZIPCodeInput, PHONE_NUMBER_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_PHONE_NUMBER_COMPLETED.getMessage(phoneNumber));
	}
	
	public void typeSSN(String SSN) {
		type(SSN, SSNInput, SSN_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_SSN_COMPLETED.getMessage(SSN));
	}
	
	public void typeUsername(String username) {
		type(username, usernameInput, USERNAME_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_USERNAME_COMPLETED.getMessage(username));
	}
	
	public void typePassword(String password) {
		type(password, passwordInput, PASSWORD_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_PASSWORD_COMPLETED.getMessage());
	}
	
	public void typePasswordConfirm(String passwordConfirm) {
		type(passwordConfirm, passwordConfirmInput, PASSWORD_CONFIRM_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_PASSWORD_CONFIRM_COMPLETED.getMessage());
	}
	
	public void clickRegisterButton() {
		click(registerButton, REGISTER_BUTTON_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_REGISTER_BUTTON_CLICKED.getMessage(REGISTER_BUTTON_NAME));
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
	public String getWelcomeTitleText() {
		String text = getText(welcomeTitle, WELCOME_TITLE_NAME);
		
		return text;
	}
	
	public String getRegistrationSuccessMessageText() {
		String text = getText(registrationSuccessMessage, REGISTRATION_SUCCESS_MESSAGE_NAME);
		
		return text;
	}
	
	// Validate
	public boolean isWelcomeTitleVisible() {		
		try {
			elementVisible(welcomeTitle, WELCOME_TITLE_NAME);
	        return true;
		} catch (AutomationException e) {
			return false;
		}
	}
	
	public boolean isRegistrationSuccessMessageVisible() {
		try {
			elementVisible(registrationSuccessMessage, REGISTRATION_SUCCESS_MESSAGE_NAME);
			return true;
		} catch (AutomationException e) {
			return false;
		}
	}
}
