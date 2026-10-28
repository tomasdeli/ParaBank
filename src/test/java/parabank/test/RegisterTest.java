package parabank.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import parabank.data.Data;
import parabank.data.RegisterData;
import parabank.utils.Messages;

public class RegisterTest extends BaseTest {
    /*** METHODS ***/
    // Tests
    @Test(dataProvider = "Register", dataProviderClass = Data.class, priority = 1)
    public void registerTest(RegisterData data) {
        String testCase = "REGISTRO - " + data.getTestCase();
        ExtentTest test = report.createTest("Register Test - " + data.getTestCase());

        try {
            LOGGER.info(Messages.TEST_START.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_START.getMessage(testCase));

            validateData(data);

            LOGGER.info("\n\n*** INICIANDO REGISTRO ***");

            logInPage.clickRegisterButton();

            registerPage.register(data.getFirstName(), data.getLastName(), data.getAddress(), data.getCity(), data.getState(), data.getZIPCode(), data.getPhoneNumber(), data.getSSN(), data.getUsername(), data.getPassword(), data.getPasswordConfirm());

            LOGGER.info("\n*** FINALIZANDO REGISTRO ***\n");
            test.log(Status.INFO, Messages.REGISTER_COMPLETED.getMessage());

            String expectedWelcomeTitle = "Welcome " + data.getUsername();

            validateExpectedMessage(data.getExpectedMessage());

            Assert.assertTrue(homePage.isWelcomeTitleVisible(), Messages.TEXT_NOT_VISIBLE.getMessage(expectedWelcomeTitle));
            Assert.assertEquals(homePage.getWelcomeTitleText(), expectedWelcomeTitle, Messages.TEXT_MISMATCH.getMessage(homePage.getWelcomeTitleText(), expectedWelcomeTitle));

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

    @Test(dataProvider = "RegisterExistingUser", dataProviderClass = Data.class, priority = 2)
    public void registerTestExistingUserTest(RegisterData data) {
        String testCase = "REGISTRO - USUARIO EXISTENTE - " + data.getTestCase();
        ExtentTest test = report.createTest("Register Test - Existing User - " + data.getTestCase());

        try {
            LOGGER.info(Messages.TEST_START.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_START.getMessage(testCase));

            validateData(data);

            LOGGER.info("\n\n*** INICIANDO REGISTRO ***");

            logInPage.clickRegisterButton();

            registerPage.register(data.getFirstName(), data.getLastName(), data.getAddress(), data.getCity(), data.getState(), data.getZIPCode(), data.getPhoneNumber(), data.getSSN(), data.getUsername(), data.getPassword(), data.getPasswordConfirm());

            LOGGER.info("\n*** FINALIZANDO REGISTRO ***\n");
            test.log(Status.INFO, Messages.REGISTER_COMPLETED.getMessage());

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

    @Test(dataProvider = "RegisterEmptyFields", dataProviderClass = Data.class, priority = 3)
    public void registerTestEmptyFieldsTest(RegisterData data) {
        String testCase = "REGISTRO - CAMPOS VACÍOS - " + data.getTestCase();
        ExtentTest test = report.createTest("Register Test - Empty Fields - " + data.getTestCase());

        try {
            LOGGER.info(Messages.TEST_START.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_START.getMessage(testCase));

            LOGGER.info("\n\n*** INICIANDO REGISTRO ***");

            logInPage.clickRegisterButton();

            registerPage.register(data.getFirstName(), data.getLastName(), data.getAddress(), data.getCity(), data.getState(), data.getZIPCode(), data.getPhoneNumber(), data.getSSN(),data.getUsername(), data.getPassword(), data.getPasswordConfirm());

            LOGGER.info("\n*** FINALIZANDO REGISTRO ***\n");
            test.log(Status.INFO, Messages.REGISTER_COMPLETED.getMessage());

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
    
    @Test(dataProvider = "RegisterPasswordsMismatch", dataProviderClass = Data.class, priority = 4)
    public void registerPasswordsMismatchTest(RegisterData data) {
        String testCase = "REGISTRO - CONTRASEÑAS DIFERENTES - " + data.getTestCase();
        ExtentTest test = report.createTest("Register Test - Passwords Mismatch - " + data.getTestCase());

        try {
            LOGGER.info(Messages.TEST_START.getMessage(testCase));
            test.log(Status.INFO, Messages.TEST_START.getMessage(testCase));

            validateData(data);

            LOGGER.info("\n\n*** INICIANDO REGISTRO ***");

            logInPage.clickRegisterButton();

            registerPage.register(data.getFirstName(), data.getLastName(), data.getAddress(), data.getCity(), data.getState(), data.getZIPCode(), data.getPhoneNumber(), data.getSSN(), data.getUsername(), data.getPassword(), data.getPasswordConfirm());

            LOGGER.info("\n*** FINALIZANDO REGISTRO ***\n");
            test.log(Status.INFO, Messages.REGISTER_COMPLETED.getMessage());

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

    /*** VALIDATIONS ***/
    private void validateData(RegisterData data) {
        validateDataNull(data);

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

        validateErrors(errors);
    }

    private void validateExpectedMessage(String message) {
        String expectedMessage;
        String actualMessage;
        boolean isMessageVisible;

        switch (message) {
            case "REGISTRATION_SUCCESS":
                expectedMessage = "Your account was created successfully. You are now logged in.";
                isMessageVisible = homePage.isRegistrationSuccessMessageVisible();
                actualMessage = isMessageVisible ? homePage.getRegistrationSuccessMessageText(): null;
                
                break;
            case "EXISTING_USER_ERROR":
                expectedMessage = "This username already exists.";
                isMessageVisible = registerPage.isExistingUserErrorMessageVisible();
                actualMessage = isMessageVisible ? registerPage.getExistingUserErrorMessageText(): null;
               
                break;
            case "FIRST_NAME_REQUIRED_ERROR":
                expectedMessage = "First name is required.";
                isMessageVisible = registerPage.isFirstNameRequiredErrorMessageVisible();
                actualMessage = isMessageVisible ? registerPage.getFirstNameRequiredErrorMessageText() : null;
                
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
            case "PASSWORDS_MISMATCH_ERROR":
            	expectedMessage = "Passwords did not match.";
                isMessageVisible = registerPage.isPasswordsMismatchErrorMessageVisible();
                actualMessage = isMessageVisible ? registerPage.getPasswordsMismatchErrorMessageText(): null;
               
                break;
            default:
                Assert.fail(Messages.INVALID_ID_MESSAGE.getMessage(message));
                
                return;
        }

        Assert.assertTrue(isMessageVisible, Messages.TEXT_NOT_VISIBLE.getMessage(expectedMessage));
        Assert.assertEquals(actualMessage, expectedMessage, Messages.TEXT_MISMATCH.getMessage(actualMessage, expectedMessage));
    }
}