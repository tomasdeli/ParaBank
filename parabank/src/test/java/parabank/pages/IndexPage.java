package parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parabank.utils.Messages;

public class IndexPage extends BasePage{	
	/*** VARIABLES ***/
	private static final String URL = "https://parabank.parasoft.com/parabank/index.htm";
	
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexPage.class);
	
	/*** CONSTRUCTOR ***/
	public IndexPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*** METHODS ***/
	public void navigateToIndexPage() {
		navigateTo(URL);
		
		LOGGER.info(Messages.INDEX_PAGE_URL_ACCESS.getMessage(URL));
	}
}
