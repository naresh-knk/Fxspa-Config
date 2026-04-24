package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.EditServicesPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditServicesTest extends BaseTest {

    @Test
    public void verifyServicesNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditServicesPage editservicesPage = new EditServicesPage(driver);

        // Step 2: CLICK FX SPA CONFIG
        editservicesPage.clickFXSPAConfigIcon();
        editservicesPage.switchWindow();

        // Step 3: Navigate to Services
        editservicesPage.clickRandom();
        editservicesPage.clickServices();
        editservicesPage.clickRandom();

        // Step 4: Update Service
        editservicesPage.clickAdd();
        editservicesPage.updateServices();

        // Step 5: Capture Toast Message
        String actualToastMsg = editservicesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Service Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Service updated successfully",
                "Service not updated"
        );
    }
}