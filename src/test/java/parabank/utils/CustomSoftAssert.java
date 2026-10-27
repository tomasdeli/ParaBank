package parabank.utils;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class CustomSoftAssert extends SoftAssert {

	/*** VARIABLES ***/
    private final ExtentTest test;

    /*** CONSTRUCTOR ***/
    public CustomSoftAssert(ExtentTest test) {
        this.test = test;
    }

    /*** METHODS ***/
    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        test.fail(ex.getMessage());
    }
}
