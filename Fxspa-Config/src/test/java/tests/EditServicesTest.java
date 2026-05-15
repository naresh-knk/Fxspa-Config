package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.EditServicesPage;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.ServicesPage;

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
        editservicesPage.clickRow();
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

    @Test
    public void verifyResetFunctionality() {

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(config.getUsername(), config.getPassword());

    EditServicesPage editservicesPage = new EditServicesPage(driver);
    ServicesPage servicesPage = new ServicesPage(driver);

    editservicesPage.clickFXSPAConfigIcon();
    editservicesPage.switchWindow();
    servicesPage.clickRandom();
    editservicesPage.clickServices();
    servicesPage.clickRandom();
    editservicesPage.clickRow();

    // Click Reset
    editservicesPage.resetServices();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            servicesPage.getServiceNameValue(),
            "",
            "Service name reset successfully",
            "Service name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            servicesPage.getServiceDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            servicesPage.getServiceDurationValue(),
            "",
            "Duration reset successfully",
            "Duration not cleared"
    );
}
}