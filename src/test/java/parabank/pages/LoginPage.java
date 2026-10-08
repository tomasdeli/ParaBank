package parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
	/*** ELEMENTS ***/
	@FindBy(xpath = "//a[@href='register.htm']")
	private WebElement registerButton;
	
	private static final String REGISTER_BUTTON_NAME = "Register Button";
	
	/*** CONSTRUCTOR ***/
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*** METHODS ***/
	public void clickRegisterButton() {
		click(registerButton, REGISTER_BUTTON_NAME);
	}
}
