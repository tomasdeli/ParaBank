package parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import parabank.utils.AutomationException;

public class HomePage extends BasePage{
	/*** ELEMENTS ***/
	@FindBy(className = "title")
	private WebElement welcomeTitle;
	@FindBy(xpath = "//p[text()='Your account was created successfully. You are now logged in.']")
	private WebElement registrationSuccessMessage;
	@FindBy(className = "title")
	private WebElement accountsOverviewTitle;
	@FindBy(className = "smallText")
	private WebElement welcomeSubtitle;
	@FindBy(xpath = "//h2[normalize-space()='Account Services']")
	private WebElement accountServicesText;
	@FindBy(xpath = "//a[@href='openaccount.htm']")
	private WebElement openNewAccountButton;
	@FindBy(xpath = "//a[@href='overview.htm']")
	private WebElement accountsOverviewButton;
	@FindBy(xpath = "//a[@href='transfer.htm']")
	private WebElement transferFundsButton;
	@FindBy(xpath = "//a[@href='billpay.htm']")
	private WebElement billPayButton;
	@FindBy(xpath = "//a[@href='findtrans.htm']")
	private WebElement findTransactionsButton;
	@FindBy(xpath = "//a[@href='updateprofile.htm']")
	private WebElement updateContactInfoButton;
	@FindBy(xpath = "//a[@href='requestloan.htm']")
	private WebElement requestLoanButton;
	@FindBy(xpath = "//a[@href='logout.htm']")
	private WebElement logOutButton;
	
	private static final String WELCOME_TITLE_NAME = "Welcome Title";
	private static final String REGISTRATION_SUCCESS_MESSAGE_NAME = "Registration Success Message";
	private static final String ACCOUNTS_OVERVIEW_TITLE_NAME = "Account Overview Title";
	private static final String WELCOME_SUBTITLE_NAME = "Welcome subtitle";
	private static final String ACCOUNT_SERVICES_TEXT_NAME = "Account Services";
	private static final String OPEN_NEW_ACCOUNT_BUTTON_NAME = "Open New Account button";
	private static final String ACCOUNTS_OVERVIEW_BUTTON_NAME = "Accounts Overview button";
	private static final String TRANSFER_FUNDS_BUTTON_NAME = "Transfer Funds button";
	private static final String BILL_PAY_BUTTON_NAME = "Bill Pay button";
	private static final String FIND_TRANSACTIONS_BUTTON_NAME = "Find Transactions button";
	private static final String UPDATE_CONTACT_INFO_BUTTON_NAME = "Update Contact Info button";
	private static final String REQUEST_LOAN_BUTTON_NAME = "Request Loan button";
	private static final String LOG_OUT_BUTTON_NAME = "Log Out button";
	
	/*** CONSTRUCTOR ***/
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
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
	
	public String getAccountsOverviewTitleText() {
		String text = getText(accountsOverviewTitle, ACCOUNTS_OVERVIEW_TITLE_NAME);
		
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
	
	public boolean isAccountsOverviewTitleVisible() {		
		try {
			elementVisible(accountsOverviewTitle, ACCOUNTS_OVERVIEW_TITLE_NAME);
		    	return true;
		} catch (AutomationException e) {
				return false;
		}
	}
	
	public boolean isWelcomeSubtitleVisible() {
	    try {
	        elementVisible(welcomeSubtitle, WELCOME_SUBTITLE_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isAccountServicesTextVisible() {
	    try {
	        elementVisible(accountServicesText, ACCOUNT_SERVICES_TEXT_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isOpenNewAccountButtonVisible() {
	    try {
	        elementVisible(openNewAccountButton, OPEN_NEW_ACCOUNT_BUTTON_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isAccountsOverviewButtonVisible() {
	    try {
	        elementVisible(accountsOverviewButton, ACCOUNTS_OVERVIEW_BUTTON_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isTransferFundsButtonVisible() {
	    try {
	        elementVisible(transferFundsButton, TRANSFER_FUNDS_BUTTON_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isBillPayButtonVisible() {
	    try {
	        elementVisible(billPayButton, BILL_PAY_BUTTON_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isFindTransactionsButtonVisible() {
	    try {
	        elementVisible(findTransactionsButton, FIND_TRANSACTIONS_BUTTON_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isUpdateContactInfoButtonVisible() {
	    try {
	        elementVisible(updateContactInfoButton, UPDATE_CONTACT_INFO_BUTTON_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isRequestLoanButtonVisible() {
	    try {
	        elementVisible(requestLoanButton, REQUEST_LOAN_BUTTON_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}

	public boolean isLogOutButtonVisible() {
	    try {
	        elementVisible(logOutButton, LOG_OUT_BUTTON_NAME);
	        return true;
	    } catch (AutomationException e) {
	        return false;
	    }
	}
}
