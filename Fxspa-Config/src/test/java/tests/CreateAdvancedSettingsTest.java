package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.CreateAdvancedSettingsPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class CreateAdvancedSettingsTest extends BaseTest {

    @Test
    public void verifyadvancedSettingsNavigation() throws InterruptedException {

        CreateAdvancedSettingsPage advancedSettingsPage = new CreateAdvancedSettingsPage(driver);
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


//     CreateAdvancedSettingsPage AdvancedSettingsPage = new CreateAdvancedSettingsPage(driver);
//     ServicesPage servicesPage=new ServicesPage(driver);
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
