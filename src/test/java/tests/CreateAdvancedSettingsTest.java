package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.CreateAdvancedSettingsPage;
import com.idsnext.pages.CreateAdvancedSettingsPage;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.ServicesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class CreateAdvancedSettingsTest extends BaseTest {

    @Test
    public void verifyadvancedSettingsNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        CreateAdvancedSettingsPage advancedSettingsPage = new CreateAdvancedSettingsPage(driver);

        // Step 2: CLICK FX REPORTS     
        advancedSettingsPage.clickFXSPAConfigIcon();
        advancedSettingsPage.switchWindow();

        // Step 3: Open 3-dot menu
        advancedSettingsPage.clickRandom();
        advancedSettingsPage.clickAdvancedSettings();
        advancedSettingsPage.clickRandom();
        advancedSettingsPage.clickAdd();
        advancedSettingsPage.createAdvancedSettings();

          // Step 4: Capture Toast Message
        String actualToastMsg = advancedSettingsPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Advanced Settings Successfully Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Advanced Settings created successfully",
                "Advanced Settings not created"
        );

    }

//      @Test
//     public void verifyResetFunctionality() {

//     LoginPage loginPage = new LoginPage(driver);
//     loginPage.login(config.getUsername(), config.getPassword());

//     CreateAdvancedSettingsPage AdvancedSettingsPage = new CreateAdvancedSettingsPage(driver);
//     ServicesPage servicesPage=new ServicesPage(driver);

//     AdvancedSettingsPage.clickFXSPAConfigIcon();
//     AdvancedSettingsPage.switchWindow();

//     servicesPage.clickRandom();
//     AdvancedSettingsPage.clickAdvancedSettings();
//     servicesPage.clickRandom();
//     AdvancedSettingsPage.clickAdd();

//     // Click Reset
//     AdvancedSettingsPage.resetAdvancedSettings();

//     // Assertions

//    AssertionUtils.assertEqualsWithMessage(
//             AdvancedSettingsPage.getAdvanceSettingsNameValue(),
//             "",
//             "Advanced Settings name reset successfully",
//             "Advanced Settings name not cleared"
//     );

//     AssertionUtils.assertEqualsWithMessage(
//             AdvancedSettingsPage.getAdvanceSettingsDescriptionValue(),
//             "",
//             "Description reset successfully",
//             "Description not cleared"
//     );

}
