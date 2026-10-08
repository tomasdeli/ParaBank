package parabank.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import parabank.utils.AutomationException;
import parabank.utils.Messages;

public class BasePage {
	/*** VARIABLES ***/
	// Driver
	protected final WebDriver driver;
	
	// Waits
	protected final WebDriverWait shortWait;
	protected final WebDriverWait longWait;

	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
	
	/*** CONSTRUCTOR ***/
	public BasePage(WebDriver driver) {
		this.driver = driver;
		
		shortWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		longWait = new WebDriverWait(driver, Duration.ofSeconds(120));
	}

	/*** METHODS ***/
	// Actions
	protected void navigateTo(String URL) {
		try {
			driver.get(URL);
			
			LOGGER.info(Messages.ACCESS_URL_OK.getMessage(URL));
		} catch (Exception e) {
			String message = Messages.ACCESS_URL_ERROR.getMessage(URL);
			
			LOGGER.error(message, e);
			
            throw new AutomationException(message, e);
		}
	}
	
	protected void click(WebElement element, String name) {		
		try {
			WebElement elementClickable = elementClickable(element, name);
			
			elementClickable.click();
			
			LOGGER.info(Messages.CLICK_ELEMENT_OK.getMessage(name));
		} catch (AutomationException e) {
			throw e;
		} catch (Exception e) {
			String message = Messages.CLICK_ELEMENT_ERROR.getMessage(name);
			
			LOGGER.error(message, e);
			
			throw new AutomationException(message, e);
		}
	}

	protected void type(String text, WebElement element, String name) {		
		try {
			WebElement elementClickable = elementClickable(element, name);		
			
			elementClickable.click();
			elementClickable.clear();
			elementClickable.sendKeys(text);
			
			LOGGER.info(Messages.TYPE_OK.getMessage(text, name));
		} catch (AutomationException e) {
			throw e;
		} catch (Exception e) {
			String message = Messages.TYPE_ERROR.getMessage(name);
			
			LOGGER.error(message, e);
			
			throw new AutomationException(message, e);
		}
	}

	protected String getText(WebElement element, String name) {		
		try {
			WebElement elementVisible = elementVisible(element, name);
			String text = elementVisible.getText();
			
			LOGGER.info(Messages.GET_TEXT_OK.getMessage(name));
			
			return text;
		} catch (AutomationException e) {
			throw e;
		} catch (Exception e) {
			String message = Messages.GET_TEXT_ERROR.getMessage(name);
			
			LOGGER.error(message, e);
			
			throw new AutomationException(message, e);
		}
	}

	protected String getAttribute(WebElement element, String name, String attribute) {
		try {
			WebElement elementVisible = elementVisible(element, name);
			String elementAttribute = elementVisible.getAttribute(attribute);
			
			LOGGER.info(Messages.GET_ATTRIBUTE_OK.getMessage(name));

			return elementAttribute;
		} catch (AutomationException e) {
			throw e;
		} catch (Exception e) {
			String message = Messages.GET_ATTRIBUTE_ERROR.getMessage(name);
			
			LOGGER.error(message, e);
			
			throw new AutomationException(message, e);
		}
	}

	// Validations
	protected WebElement elementClickable(WebElement element, String name) {		
		try {
			WebElement elementClickable = shortWait.until(ExpectedConditions.elementToBeClickable(element));
			
			return elementClickable;
		} catch (Exception e) {
			throw handleWaitException(name, e);
		}
	}

	protected WebElement elementVisible(WebElement element, String name) {		
		try {
			WebElement elementVisible = shortWait.until(ExpectedConditions.visibilityOf(element));
			
			return elementVisible;
		} catch (Exception e) {
			throw handleWaitException(name, e);
		}
	}
	
	protected void elementInvisible(WebElement element, String name) {
	    try {
	        shortWait.until(ExpectedConditions.invisibilityOf(element));
	    } catch (Exception e) {
	        throw handleWaitException(name, e);
	    }
	}

	protected void elementToBeSelected(WebElement element, String name) {
		try {
			shortWait.until(ExpectedConditions.elementToBeSelected(element));
		} catch (Exception e) {
			throw handleWaitException(name, e);
		}
	}

	protected Alert alertIsPresent(String name) {		
		try {
			Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
			
			return alert;
		} catch (Exception e) {
			throw handleWaitException(name, e);
		}
	}
	
	protected boolean isDisplayed(WebElement element) {
	    try {
	    	Boolean isDisplayed = element.isDisplayed();
	    	
	        return isDisplayed;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
	protected AutomationException handleWaitException(String name, Exception e) {
		String message;
		
	    if (e instanceof TimeoutException) {
	    	message = Messages.EXPIRATION_ERROR.getMessage(name);	        
	    } else if (e instanceof NoSuchElementException) {	    	
	    	message = Messages.NO_SUCH_ELEMENT_ERROR.getMessage(name);
	    } else {
	    	message = Messages.UNEXPECTED_ERROR.getMessage(name);
	    }
	    
	    LOGGER.error(message, e);
	    
	    return new AutomationException(message, e);
	}
}