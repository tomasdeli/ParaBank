package parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parabank.utils.Messages;

public class LoginPage extends BasePage{
	/*** ELEMENTS ***/
	@FindBy(linkText = "Register")
	private WebElement registerButton;
	
	private static final String REGISTER_BUTTON_NAME = "Register Button";
	
	// Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
	
	/*** CONSTRUCTOR ***/
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*** METHODS ***/
	public void clickRegisterButton() {
		click(registerButton, REGISTER_BUTTON_NAME);
		
		LOGGER.info(Messages.LOGIN_PAGE_REGISTER_BUTTON_CLICKED.getMessage(REGISTER_BUTTON_NAME));
	}
}
