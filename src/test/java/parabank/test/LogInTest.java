
package parabank.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import parabank.data.Data;
import parabank.data.LogInData;
import parabank.utils.Messages;

public class LogInTest extends BaseTest {
    /*** METHODS ***/
    // Tests
    @Test(dataProvider = "LogIn", dataProviderClass = Data.class, priority = 1)
    public void logInTest(LogInData data) {
        String testCase = data.getTestCase() + " - LOGIN";
        ExtentTest test = report.createTest(data.getTestCase() + " - Login");

        try {
            LOGGER.info(Messages.TEST_START.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_START.getMessage(testCase));

            validateData(data);

            LOGGER.info("\n\n*** INICIANDO LOGIN ***");

            logInPage.logIn(data.getUsername(), data.getPassword());

            LOGGER.info("\n*** FINALIZANDO LOGIN ***\n");
            test.log(Status.INFO, Messages.LOGIN_COMPLETED.getMessage());

            validateExpectedMessage(data.getExpectedMessage());

            Assert.assertTrue(homePage.isWelcomeSubtitleVisible(), Messages.TEXT_NOT_VISIBLE.getMessage("Welcome"));
            Assert.assertTrue(homePage.isAccountServicesTextVisible(), Messages.TEXT_NOT_VISIBLE.getMessage("Account Services"));
            Assert.assertTrue(homePage.isOpenNewAccountButtonVisible(), Messages.BUTTON_NOT_VISIBLE.getMessage("Open New Account"));
            Assert.assertTrue(homePage.isAccountsOverviewButtonVisible(), Messages.BUTTON_NOT_VISIBLE.getMessage("Accounts Overview"));
            Assert.assertTrue(homePage.isTransferFundsButtonVisible(), Messages.BUTTON_NOT_VISIBLE.getMessage("Transfer Funds"));
            Assert.assertTrue(homePage.isBillPayButtonVisible(), Messages.BUTTON_NOT_VISIBLE.getMessage("Bill Pay"));
            Assert.assertTrue(homePage.isFindTransactionsButtonVisible(), Messages.BUTTON_NOT_VISIBLE.getMessage("Find Transactions"));
            Assert.assertTrue(homePage.isUpdateContactInfoButtonVisible(), Messages.BUTTON_NOT_VISIBLE.getMessage("Update Contact Info"));
            Assert.assertTrue(homePage.isRequestLoanButtonVisible(), Messages.BUTTON_NOT_VISIBLE.getMessage("Request Loan"));
            Assert.assertTrue(homePage.isLogOutButtonVisible(), Messages.BUTTON_NOT_VISIBLE.getMessage("Log Out"));

            LOGGER.info(Messages.TEST_ASSERT_PASSED.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_ASSERT_PASSED.getMessage(testCase));
            test.pass(Messages.TEST_SUCCESSFULLY.getMessage(testCase));

        } catch (AssertionError e) {
        	test.fail(e.getMessage().split(" expected ")[0]);

            LOGGER.error(Messages.TEST_ASSERT_FAILED.getMessage(testCase), e);
            throw e;
        } catch (Exception e) {
            test.fail(Messages.TEST_UNEXPECTED_ERROR.getMessage(testCase));
            test.fail(e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName());

            LOGGER.error(Messages.TEST_UNEXPECTED_ERROR.getMessage(testCase), e);
            throw e;
        } finally {
            LOGGER.info(Messages.TEST_FINISH.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_FINISH.getMessage(testCase));
        }
    }

    @Test(dataProvider = "LogInInvalidUsernameOrPassword", dataProviderClass = Data.class, priority = 2)
    public void logInInvalidUsernameOrPasswordTest(LogInData data) {
        String testCase = data.getTestCase() + " - LOGIN - USUARIO O CONTRASEÑA INVÁLIDOS";
        ExtentTest test = report.createTest(data.getTestCase() + " - Login - Invalid Username Or Password");

        try {
            LOGGER.info(Messages.TEST_START.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_START.getMessage(testCase));

            validateData(data);

            LOGGER.info("\n\n*** INICIANDO LOGIN ***");

            logInPage.logIn(data.getUsername(), data.getPassword());

            LOGGER.info("\n*** FINALIZANDO LOGIN ***\n");
            test.log(Status.INFO, Messages.LOGIN_COMPLETED.getMessage());

            validateExpectedMessage(data.getExpectedMessage());

            LOGGER.info(Messages.TEST_ASSERT_PASSED.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_ASSERT_PASSED.getMessage(testCase));
            test.pass(Messages.TEST_SUCCESSFULLY.getMessage(testCase));

        } catch (AssertionError e) {
        	test.fail(e.getMessage().split(" expected ")[0]);

            LOGGER.error(Messages.TEST_ASSERT_FAILED.getMessage(testCase), e);
            throw e;
        } catch (Exception e) {
            test.fail(Messages.TEST_UNEXPECTED_ERROR.getMessage(testCase));
            test.fail(e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName());

            LOGGER.error(Messages.TEST_UNEXPECTED_ERROR.getMessage(testCase), e);
            throw e;
        } finally {
            LOGGER.info(Messages.TEST_FINISH.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_FINISH.getMessage(testCase));
        }
    }

    @Test(dataProvider = "LogInEmptyFields", dataProviderClass = Data.class, priority = 3)
    public void logInEmptyFieldsTest(LogInData data) {
        String testCase = data.getTestCase() + " - LOGIN - CAMPOS VACÍOS";
        ExtentTest test = report.createTest(data.getTestCase() + " - Login - Empty Fields");

        try {
            LOGGER.info(Messages.TEST_START.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_START.getMessage(testCase));

            LOGGER.info("\n\n*** INICIANDO LOGIN ***");

            logInPage.logIn(data.getUsername(), data.getPassword());

            LOGGER.info("\n*** FINALIZANDO LOGIN ***\n");
            test.log(Status.INFO, Messages.LOGIN_COMPLETED.getMessage());

            List<String> expectedMessages = Arrays.asList(data.getExpectedMessage().split(";"));

            for (String expectedMessage : expectedMessages) {
                validateExpectedMessage(expectedMessage.trim());
            }

            LOGGER.info(Messages.TEST_ASSERT_PASSED.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_ASSERT_PASSED.getMessage(testCase));
            test.pass(Messages.TEST_SUCCESSFULLY.getMessage(testCase));

        } catch (AssertionError e) {
        	test.fail(e.getMessage().split(" expected ")[0]);

            LOGGER.error(Messages.TEST_ASSERT_FAILED.getMessage(testCase), e);
            throw e;
        } catch (Exception e) {
            test.fail(Messages.TEST_UNEXPECTED_ERROR.getMessage(testCase));
            test.fail(e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName());

            LOGGER.error(Messages.TEST_UNEXPECTED_ERROR.getMessage(testCase), e);
            throw e;
        } finally {
            LOGGER.info(Messages.TEST_FINISH.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_FINISH.getMessage(testCase));
        }
    }

    private void validateData(LogInData data) {
        validateDataNull(data);

        List<String> errors = new ArrayList<>();

        validateField(data.getUsername(), "USERNAME", errors);
        validateField(data.getPassword(), "PASSWORD", errors);

        validateErrors(errors);
    }

    private void validateExpectedMessage(String message) {
        String expectedMessage;
        String actualMessage;
        boolean isMessageVisible;

        switch (message) {
            case "LOGIN_SUCCESS":
                expectedMessage = "Accounts Overview";
                isMessageVisible = homePage.isAccountsOverviewTitleVisible();
                actualMessage = isMessageVisible ? homePage.getAccountsOverviewTitleText() : null;
                
                break;
            case "INVALID_USERNAME_OR_PASSWORD_ERROR":
                expectedMessage = "The username and password could not be verified.";
                isMessageVisible = logInPage.isInvalidUsernameOrPasswordErrorMessageVisible();
                actualMessage = isMessageVisible ? logInPage.getInvalidUsernameOrPasswordErrorMessageText(): null;
               
                break;
            case "USERNAME_REQUIRED_ERROR":
            case "PASSWORD_REQUIRED_ERROR":
                expectedMessage = "Please enter a username and password.";
                isMessageVisible = logInPage.isUsernameOrPasswordRequiredErrorMessageVisible();
                actualMessage = isMessageVisible ? logInPage.getUsernameOrPasswordRequiredErrorMessageText(): null;
                
                break;
            default:
                Assert.fail(Messages.INVALID_ID_MESSAGE.getMessage(message));
                
                return;
        }

        Assert.assertTrue(isMessageVisible, Messages.TEXT_NOT_VISIBLE.getMessage(expectedMessage));
        Assert.assertEquals(actualMessage, expectedMessage, Messages.TEXT_MISMATCH.getMessage(actualMessage, expectedMessage));
    }
}
