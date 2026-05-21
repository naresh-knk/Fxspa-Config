package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.LoginPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Assertion using AssertionUtils
        AssertionUtils.assertTrueWithMessage(
            loginPage.isLoginSuccessful(),   // condition
            "Login successful ",           // success message
            "Login failed "                // failure message
        );
    }
}

