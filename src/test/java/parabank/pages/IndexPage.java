package parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class IndexPage extends BasePage{	
	/*** VARIABLES ***/
	private static final String URL = "https://parabank.parasoft.com/parabank/index.htm";
	
	/*** CONSTRUCTOR ***/
	public IndexPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*** METHODS ***/
	public void navigateToIndexPage() {
		navigateTo(URL);
	}
}
