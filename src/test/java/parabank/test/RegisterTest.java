package parabank.test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
	// Test Pre-Config
	@BeforeMethod
	public void configurateTest() throws MalformedURLException {
		LOGGER.info("\n\n*** INICIANDO CONFIGURACIÓN DE PRUEBA ***");
		
		driver = new Driver();
		driver.configurate();
					
		report = Report.configurate();
				
		indexPage = new IndexPage(driver.getDriver());
		loginPage = new LoginPage(driver.getDriver());
		registerPage = new RegisterPage(driver.getDriver());
		
		indexPage.navigateToIndexPage();
		
		LOGGER.info("\n*** FINALIZANDO CONFIGURACIÓN DE PRUEBA ***\n");
	}
	
	// Tests
	@Test(dataProvider = "RegisterData", dataProviderClass = Data.class, priority = 1)
	public void registerTest(RegisterData data) {
		ExtentTest test = report.createTest("Register Test - " + data.getTestCase());
		SoftAssert softAssert = new SoftAssert();
		
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
			String expectedRegistrationSuccessMessage = "Your account was created successfully. You are now logged in.";
			
			softAssert.assertTrue(registerPage.isWelcomeTitleVisible(), Messages.REGISTER_PAGE_WELCOME_TITLE_NOT_VISIBLE.getMessage(data.getTestCase()));
			softAssert.assertEquals(registerPage.getWelcomeTitleText(), expectedWelcomeTitle, Messages.REGISTER_PAGE_WELCOME_TITLE_TEXT_MISMATCH.getMessage(expectedWelcomeTitle));
			softAssert.assertTrue(registerPage.isRegistrationSuccessMessageVisible(), Messages.REGISTER_PAGE_REGISTRATION_SUCCESS_MESSAGE_NOT_VISIBLE.getMessage(data.getTestCase()));
			softAssert.assertEquals(registerPage.getRegistrationSuccessMessageText(), expectedRegistrationSuccessMessage, Messages.REGISTER_PAGE_REGISTRATION_SUCCESS_MESSAGE_TEXT_MISMATCH.getMessage(expectedRegistrationSuccessMessage));
			softAssert.assertAll();
			
			LOGGER.info(Messages.TEST_ASSERT_PASSED.getMessage("REGISTRO - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_ASSERT_PASSED.getMessage("REGISTRO - " + data.getTestCase()));
			test.pass(Messages.TEST_SUCCESSFULLY.getMessage("REGISTRO - " + data.getTestCase()));
		} catch (AssertionError e) {
			LOGGER.error(Messages.TEST_ASSERT_FAILED.getMessage(data.getTestCase()), e);
			
	        test.fail(e);
	        
	        throw e;
		} catch (Exception e) {
			LOGGER.error(Messages.TEST_UNEXPECTED_ERROR.getMessage(data.getTestCase()), e);
			
	        test.fail(e);
	        throw e;
		} finally {
			LOGGER.info(Messages.TEST_FINISH.getMessage("REGISTRO - " + data.getTestCase()));
			test.log(Status.INFO, Messages.TEST_FINISH.getMessage("REGISTRO - " + data.getTestCase()));
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
	
    @AfterMethod
    public void finishTest() {
        try {
            report.flush();
            LOGGER.info(Messages.REPORT_GENERATED_OK.getMessage());
        } catch (Exception e) {
            LOGGER.error(Messages.REPORT_GENERATED_ERROR.getMessage(), e);
        }

        try {
            driver.finish();
            LOGGER.info(Messages.DRIVER_FINISHED_OK.getMessage());
        } catch (Exception e) {
            LOGGER.error(Messages.DRIVER_FINISHED_ERROR.getMessage(), e);
        }
    }
}