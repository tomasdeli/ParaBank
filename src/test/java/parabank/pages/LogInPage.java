package parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parabank.utils.AutomationException;
import parabank.utils.Messages;

public class LogInPage extends BasePage{
	/*** VARIABLES ***/
	// Elements
	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameInput;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordInput;
	@FindBy(xpath = "//input[@value='Log In']")
	private WebElement logInButton;
	@FindBy(linkText = "Register")
	private WebElement registerButton;
	@FindBy(className = "error")
	private WebElement invalidUsernameOrPasswordErrorMessage;
	@FindBy(className = "error")
	private WebElement inexistentUserErrorMessage;
	@FindBy(className = "error")
	private WebElement usernameOrPasswordRequiredErrorMessage;
	
	private static final String USERNAME_INPUT_NAME = "Username Input";
	private static final String PASSWORD_INPUT_NAME = "Password Input";
	private static final String LOG_IN_BUTTON_NAME = "LogIn Button";
	private static final String REGISTER_BUTTON_NAME = "Register Button";
	private static final String INVALID_USERNAME_OR_PASSWORD_ERROR_MESSAGE_NAME = "Wrong Password Error Message";
	private static final String INEXISTENT_USER_ERROR_MESSAGE_NAME = "Inexistent User Error Message";
	private static final String USERNAME_OR_PASSWORD_REQUIRED_ERROR_MESSAGE_NAME = "Username Or Password Error Message";
	
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(LogInPage.class);
	
	/*** CONSTRUCTOR ***/
	public LogInPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*** METHODS ***/
	// Actions
	public void clickRegisterButton() {
		click(registerButton, REGISTER_BUTTON_NAME);
	}
	
	public void typeUsername(String username) {
		type(username, usernameInput, USERNAME_INPUT_NAME);
	}
	
	public void typePassword(String password) {
		type(password, passwordInput, PASSWORD_INPUT_NAME);
	}
	
	public void clickLogInButton() {
		click(logInButton, LOG_IN_BUTTON_NAME);
	}
	
	public void logIn(String username, String password) {
		typeUsername(username);
		typePassword(password);
		clickLogInButton();
		LOGGER.info(Messages.LOGIN_COMPLETED.getMessage());
	}
	
	// Getter
		public String getInvalidUsernameOrPasswordErrorMessageText() {
			String text = getText(invalidUsernameOrPasswordErrorMessage, INVALID_USERNAME_OR_PASSWORD_ERROR_MESSAGE_NAME);
			
			return text;
		}
		
		public String getInexistentUserErrorMessageText() {
			String text = getText(inexistentUserErrorMessage, INEXISTENT_USER_ERROR_MESSAGE_NAME);
			
			return text;
		}
		
		public String getUsernameOrPasswordRequiredErrorMessageText() {
			String text = getText(usernameOrPasswordRequiredErrorMessage, USERNAME_OR_PASSWORD_REQUIRED_ERROR_MESSAGE_NAME);
			
			return text;
		}
	
	// Validate
	public boolean isInvalidUsernameOrPasswordErrorMessageVisible() {
		try {
			elementVisible(invalidUsernameOrPasswordErrorMessage, INVALID_USERNAME_OR_PASSWORD_ERROR_MESSAGE_NAME);
			return true;
		} catch(AutomationException e) {
			return false;
		}
	}
	
	public boolean isInexistentUserErrorMessageVisible() {
		try {
			elementVisible(inexistentUserErrorMessage, INEXISTENT_USER_ERROR_MESSAGE_NAME);
			return true;
		} catch(AutomationException e) {
			return false;
		}
	}
	
	public boolean isUsernameOrPasswordRequiredErrorMessageVisible() {
		try {
			elementVisible(usernameOrPasswordRequiredErrorMessage, USERNAME_OR_PASSWORD_REQUIRED_ERROR_MESSAGE_NAME);
			return true;
		} catch(AutomationException e) {
			return false;
		}
	}
}
