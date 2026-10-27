package parabank.test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import parabank.data.Data;
import parabank.data.RegisterData;
import parabank.driver.Driver;
import parabank.pages.IndexPage;
import parabank.pages.LoginPage;
import parabank.pages.RegisterPage;
import parabank.report.Report;
import parabank.utils.CustomSoftAssert;
import parabank.utils.Messages;

public class RegisterTest {
	/*** VARIABLES ***/
	// Driver
	private Driver driver;
	
	// Pages
	private IndexPage indexPage;
	private LoginPage loginPage;
	private RegisterPage registerPage;
	
	// Report
	private ExtentReports report;
	
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterTest.class);
	
	/*** METHODS ***/
	@BeforeSuite
    public void configurateReport() {
        report = Report.configurate();
    }
	
	// Test Pre-Config
	@BeforeMethod
	public void configurateTest() throws MalformedURLException {
		LOGGER.info("\n\n*** INICIANDO CONFIGURACIÓN ***");
		
		driver = new Driver();
		driver.configurate();
									
		indexPage = new IndexPage(driver.getDriver());
		loginPage = new LoginPage(driver.getDriver());
		registerPage = new RegisterPage(driver.getDriver());
		
		indexPage.navigateToIndexPage();
		
		LOGGER.info("\n*** FINALIZANDO CONFIGURACIÓN ***\n");
	}
	
	// Tests
	@Test(dataProvider = "RegisterData", dataProviderClass = Data.class, priority = 1)
	public void registerTest(RegisterData data) {
		ExtentTest test = report.createTest("Register Test - " + data.getTestCase());
		CustomSoftAssert softAssert = new CustomSoftAssert(test);
		
		try {
			LOGGER.info(Messages.TEST_START.getMessage("REGISTRO - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_START.getMessage("REGISTRO - " + data.getTestCase()));
			
			validateData(data);
			
			LOGGER.info("\n\n*** INICIANDO REGISTRO ***");
			
			loginPage.clickRegisterButton();
			
			registerPage.register(data.getFirstName(), data.getLastName(), data.getAddress(), data.getCity(), data.getState(), data.getZIPCode(), data.getPhoneNumber(), data.getSSN(), data.getUsername(), data.getPassword(), data.getPasswordConfirm());
			
			LOGGER.info("\n*** FINALIZANDO REGISTRO ***\n");
			test.log(Status.INFO, Messages.REGISTER_COMPLETED.getMessage());
			
			String expectedWelcomeTitle = "Welcome " + data.getUsername();
			
			validateExpectedMessage(data.getExpectedMessage(), softAssert);
			
			softAssert.assertTrue(registerPage.isWelcomeTitleVisible(), Messages.TEXT_NOT_VISIBLE.getMessage(expectedWelcomeTitle));
			softAssert.assertEquals(registerPage.getWelcomeTitleText(), expectedWelcomeTitle, Messages.TEXT_MISMATCH.getMessage(registerPage.getWelcomeTitleText(), expectedWelcomeTitle));
			softAssert.assertAll();
			
			LOGGER.info(Messages.TEST_ASSERT_PASSED.getMessage("REGISTRO - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_ASSERT_PASSED.getMessage("REGISTRO - " + data.getTestCase()));
			test.pass(Messages.TEST_SUCCESSFULLY.getMessage("REGISTRO - " + data.getTestCase()));
		} catch (AssertionError e) {
			LOGGER.error(Messages.TEST_ASSERT_FAILED.getMessage(data.getTestCase()), e);
	        
	        throw e;
		} catch (Exception e) {
			LOGGER.error(Messages.TEST_UNEXPECTED_ERROR.getMessage(data.getTestCase()), e);
	        
	        throw e;
		} finally {
			LOGGER.info(Messages.TEST_FINISH.getMessage("REGISTRO - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_FINISH.getMessage("REGISTRO - " + data.getTestCase()));
		}
	}
	
	@Test(dataProvider = "RegisterExistingUser", dataProviderClass = Data.class, priority = 2)
	public void registerTestExistingUser(RegisterData data) {
		ExtentTest test = report.createTest("Register Test - Existing User - " + data.getTestCase());
		CustomSoftAssert softAssert = new CustomSoftAssert(test);
		
		try {
			LOGGER.info(Messages.TEST_START.getMessage("REGISTRO - USUARIO EXISTENTE - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_START.getMessage("REGISTRO - USUARIO EXISTENTE - " + data.getTestCase()));
			
			validateData(data);
			
			LOGGER.info("\n\n*** INICIANDO REGISTRO ***");
			
			loginPage.clickRegisterButton();
			
			registerPage.register(data.getFirstName(), data.getLastName(), data.getAddress(), data.getCity(), data.getState(), data.getZIPCode(), data.getPhoneNumber(), data.getSSN(), data.getUsername(), data.getPassword(), data.getPasswordConfirm());
			
			LOGGER.info("\n*** FINALIZANDO REGISTRO ***\n");
			test.log(Status.INFO, Messages.REGISTER_COMPLETED.getMessage());
			
			validateExpectedMessage(data.getExpectedMessage(), softAssert);
			softAssert.assertAll();
			
			LOGGER.info(Messages.TEST_ASSERT_PASSED.getMessage("REGISTRO - USUARIO EXISTENTE - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_ASSERT_PASSED.getMessage("REGISTRO - USUARIO EXISTENTE - " + data.getTestCase()));
			test.pass(Messages.TEST_SUCCESSFULLY.getMessage("REGISTRO - USUARIO EXISTENTE - " + data.getTestCase()));
		} catch (AssertionError e) {
			LOGGER.error(Messages.TEST_ASSERT_FAILED.getMessage(data.getTestCase()), e);
	        
	        throw e;
		} catch (Exception e) {
			LOGGER.error(Messages.TEST_UNEXPECTED_ERROR.getMessage(data.getTestCase()), e);
	        
	        throw e;
		} finally {
			LOGGER.info(Messages.TEST_FINISH.getMessage("REGISTRO - USUARIO EXISTENTE - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_FINISH.getMessage("REGISTRO - USUARIO EXISTENTE - " + data.getTestCase()));
		}
	}
	
	@Test(dataProvider = "RegisterEmptyFields", dataProviderClass = Data.class, priority = 3)
	public void registerTestEmptyFields(RegisterData data) {
		ExtentTest test = report.createTest("Register Test - Empty Fields - " + data.getTestCase());
		CustomSoftAssert softAssert = new CustomSoftAssert(test);
		
		try {
			LOGGER.info(Messages.TEST_START.getMessage("REGISTRO - CAMPOS VACÍOS - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_START.getMessage("REGISTRO - CAMPOS VACÍOS - " + data.getTestCase()));
			
			LOGGER.info("\n\n*** INICIANDO REGISTRO ***");
			
			loginPage.clickRegisterButton();
			
			registerPage.register(data.getFirstName(), data.getLastName(), data.getAddress(), data.getCity(), data.getState(), data.getZIPCode(), data.getPhoneNumber(), data.getSSN(), data.getUsername(), data.getPassword(), data.getPasswordConfirm());
			
			LOGGER.info("\n*** FINALIZANDO REGISTRO ***\n");
			test.log(Status.INFO, Messages.REGISTER_COMPLETED.getMessage());
			
			List<String> expectedMessages = Arrays.asList(data.getExpectedMessage().split(";"));
			
			for (String expecteedMessage : expectedMessages) {
		        validateExpectedMessage(expecteedMessage.trim(), softAssert);
		    }
				
			softAssert.assertAll();
			
			LOGGER.info(Messages.TEST_ASSERT_PASSED.getMessage("REGISTRO - CAMPOS VACÍOS - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_ASSERT_PASSED.getMessage("REGISTRO - CAMPOS VACÍOS - " + data.getTestCase()));
			test.pass(Messages.TEST_SUCCESSFULLY.getMessage("REGISTRO - CAMPOS VACÍOS - " + data.getTestCase()));
		} catch (AssertionError e) {
			LOGGER.error(Messages.TEST_ASSERT_FAILED.getMessage(data.getTestCase()), e);
	        
	        throw e;
		} catch (Exception e) {
			LOGGER.error(Messages.TEST_UNEXPECTED_ERROR.getMessage(data.getTestCase()), e);
	        
	        throw e;
		} finally {
			LOGGER.info(Messages.TEST_FINISH.getMessage("REGISTRO - CAMPOS VACÍOS - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_FINISH.getMessage("REGISTRO - CAMPOS VACÍOS - " + data.getTestCase()));
		}
	}
	
	 private void validateData(RegisterData data) {
	        if (data == null) {
	        	String message = Messages.TEST_TOTAL_DATA_NULL.getMessage();
	        	
	            LOGGER.error(message);

	            Assert.fail(message);
	        }

	        List<String> errors = new ArrayList<>();

	        validateField(data.getFirstName(), "FIRST_NAME", errors);
	        validateField(data.getLastName(), "LAST_NAME", errors);
	        validateField(data.getAddress(), "ADDRESS", errors);
	        validateField(data.getCity(), "CITY", errors);
	        validateField(data.getState(), "STATE", errors);
	        validateField(data.getZIPCode(), "ZIP_CODE", errors);
	        validateField(data.getPhoneNumber(), "PHONE_NUMBER", errors);
	        validateField(data.getSSN(), "SSN", errors);
	        validateField(data.getUsername(), "USERNAME", errors);
	        validateField(data.getPassword(), "PASSWORD", errors);
	        validateField(data.getPasswordConfirm(), "PASSWORD_CONFIRM", errors);

	        if (!errors.isEmpty()) {
	            String message = String.join(" ", errors);

	            LOGGER.error(message);

	            Assert.fail(message);
	        }
	    }
	 
	 private void validateField(String value, String fieldName, List<String> errors) {
	        if (value == null) {
	            errors.add(Messages.TEST_DATA_NULL.getMessage(fieldName));
	        } else if (value.isBlank()) {
	            errors.add(Messages.TEST_DATA_EMPTY.getMessage(fieldName));
	        }
	    }
	 
	 private void validateExpectedMessage(String message, CustomSoftAssert softAssert) {
		    String expectedMessage;
		    String actualMessage;
		    boolean isMessageVisible;

		    switch (message) {
		    	case "REGISTRATION_SUCCESS":
		    		expectedMessage = "Your account was created successfully. You are now logged in.";
		    		isMessageVisible = registerPage.isRegistrationSuccessMessageVisible();
		    		actualMessage = isMessageVisible ? registerPage.getRegistrationSuccessMessageText() : null;
		    		break;
		    	
		    	case "EXISTING_USER_ERROR":
		    		expectedMessage = "This username already exists.";
		    		isMessageVisible = registerPage.isExistingUserErrorMessageVisible();
		    		actualMessage = isMessageVisible ? registerPage.getExistingUserErrorMessageText(): null;
		    		break;
		    		
		        case "FIRST_NAME_REQUIRED_ERROR":
		            expectedMessage = "First name is required.";
		            isMessageVisible = registerPage.isFirstNameRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getFirstNameRequiredErrorMessageText(): null;
		            break;
		            
		        case "LAST_NAME_REQUIRED_ERROR":
		            expectedMessage = "Last name is required.";
		            isMessageVisible = registerPage.isLastNameRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getLastNameRequiredErrorMessageText(): null;
		            break;
		            
		        case "ADDRESS_REQUIRED_ERROR":
		            expectedMessage = "Address is required.";
		            isMessageVisible = registerPage.isAddressRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getAddressRequiredErrorMessageText(): null;
		            break;
		            
		        case "CITY_REQUIRED_ERROR":
		            expectedMessage = "City is required.";
		            isMessageVisible = registerPage.isCityRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getCityRequiredErrorMessageText(): null;
		            break;
		            
		        case "STATE_REQUIRED_ERROR":
		            expectedMessage = "State is required.";
		            isMessageVisible = registerPage.isStateRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getStateRequiredErrorMessageText(): null;
		            break;
		            
		        case "ZIP_CODE_REQUIRED_ERROR":
		            expectedMessage = "Zip Code is required.";
		            isMessageVisible = registerPage.isZIPCodeRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getZIPCodeRequiredErrorMessageText(): null;
		            break;
		            
		        case "SSN_REQUIRED_ERROR":
		            expectedMessage = "Social Security Number is required.";
		            isMessageVisible = registerPage.isSSNRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getSSNRequiredErrorMessageText(): null;
		            break;
		            
		        case "USERNAME_REQUIRED_ERROR":
		            expectedMessage = "Username is required.";
		            isMessageVisible = registerPage.isUsernameRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getUsernameRequiredErrorMessageText(): null;
		            break;
		            
		        case "PASSWORD_REQUIRED_ERROR":
		            expectedMessage = "Password is required.";
		            isMessageVisible = registerPage.isPasswordRequiredErrorMessageVisible();
		            actualMessage = isMessageVisible ? registerPage.getPasswordRequiredErrorMessageText(): null;
		            break;
		            
		        case "PASSWORD_CONFIRM_REQUIRED_ERROR":
		        	expectedMessage = "Password confirmation is required.";
		        	isMessageVisible = registerPage.isPasswordConfirmRequiredErrorMessageVisible();
			        actualMessage = isMessageVisible ? registerPage.getPasswordConfirmRequiredErrorMessageText(): null;
			        break;

		        default:
		            Assert.fail(Messages.INVALID_ID_MESSAGE.getMessage(message));
		            return;
		    }

		    softAssert.assertTrue(isMessageVisible, Messages.TEXT_NOT_VISIBLE.getMessage(expectedMessage));
		    softAssert.assertEquals(actualMessage, expectedMessage, Messages.TEXT_MISMATCH.getMessage(actualMessage, expectedMessage));
		}
	
    @AfterMethod
    public void finishTest() {
        try {
            driver.finish();
            LOGGER.info(Messages.DRIVER_FINISHED_OK.getMessage());
        } catch (Exception e) {
            LOGGER.error(Messages.DRIVER_FINISHED_ERROR.getMessage(), e);
        }
    }
    
    @AfterSuite
    public void finishReport() {
        try {
            report.flush();
            LOGGER.info(Messages.REPORT_GENERATED_OK.getMessage());
        } catch (Exception e) {
            LOGGER.error(Messages.REPORT_GENERATED_ERROR.getMessage(), e);
        }
    }
}