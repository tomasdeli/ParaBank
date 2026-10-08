package parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parabank.utils.Messages;

public class RegisterPage extends BasePage{
	/*** ELEMENTS ***/
	@FindBy(id = "customer.firstName")
	private WebElement firstNameInput;
	
	private static final String FIRST_NAME_INPUT_NAME = "First Name Input";
	
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
	
	/*** CONSTRUCTOR ***/
	public RegisterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*** METHODS ***/
	public void typeFirstName(String firstName) {
		type(firstName, firstNameInput, FIRST_NAME_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_PAGE_FIRST_NAME_COMPLETED.getMessage(firstName));
	}
	
	public void register(String firstName) {
		type(firstName, firstNameInput, FIRST_NAME_INPUT_NAME);
		
		LOGGER.info(Messages.REGISTER_COMPLETED.getMessage());
	}

}
