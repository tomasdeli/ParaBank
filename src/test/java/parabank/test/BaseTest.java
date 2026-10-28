package parabank.test;

import java.net.MalformedURLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import parabank.driver.Driver;
import parabank.pages.IndexPage;
import parabank.pages.LogInPage;
import parabank.pages.RegisterPage;
import parabank.pages.HomePage;
import parabank.report.Report;

import parabank.utils.Messages;

public abstract class BaseTest {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    protected Driver driver;
    protected IndexPage indexPage;
    protected LogInPage logInPage;
    protected RegisterPage registerPage;
    protected HomePage homePage;
    protected ExtentReports report;
    
    @BeforeSuite
    public void configurateReport() {
        report = Report.configurate();
    }

    @BeforeMethod
    public void configurateTest() throws MalformedURLException {
    	LOGGER.info("\n\n*** INICIANDO CONFIGURACIÓN ***");
    	
    	driver = new Driver();
    	driver.configurate();

    	indexPage = new IndexPage(driver.getDriver());
    	logInPage = new LogInPage(driver.getDriver());
    	registerPage = new RegisterPage(driver.getDriver());
    	homePage = new HomePage(driver.getDriver());

    	indexPage.navigateToIndexPage();
        
        LOGGER.info("\n*** FINALIZANDO CONFIGURACIÓN ***\n");
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

    protected void validateDataNull(Object data) {
        if (data == null) {
            String message = Messages.TEST_TOTAL_DATA_NULL.getMessage();

            LOGGER.error(message);

            Assert.fail(message);
        }
    }

    protected void validateField(String value, String fieldName, List<String> errors) {
        if (value == null) {
            errors.add(Messages.TEST_DATA_NULL.getMessage(fieldName));
        } else if (value.isBlank()) {
            errors.add(Messages.TEST_DATA_EMPTY.getMessage(fieldName));
        }
    }

    protected void validateErrors(List<String> errors) {
        if (!errors.isEmpty()) {
            String message = String.join(" ", errors);

            LOGGER.error(message);

            Assert.fail(message);
        }
    }
}