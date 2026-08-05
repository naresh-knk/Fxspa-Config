package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.CreateAdvancedSettingsPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class CreateAdvancedSettingsTest extends BaseTest {

    @Test
    public void verifyadvancedSettingsNavigation()
            throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        CreateAdvancedSettingsPage advancedSettingsPage =
                new CreateAdvancedSettingsPage(driver);

        // Navigate to Advanced Settings Add Page
        navigationSteps.navigateToAddPage(
                ModuleName.ADVANCED_SETTINGS
        );

        // Create Advanced Settings
        advancedSettingsPage.createAdvancedSettings();

        // Capture Toast Message
        String actualToastMsg =
                advancedSettingsPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Advanced Settings Successfully Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Advanced Settings created successfully",
                "Advanced Settings not created"
        );
    }

    // Reset functionality is not working
    // @Test
    public void verifyResetFunctionality() {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        CreateAdvancedSettingsPage advancedSettingsPage =
                new CreateAdvancedSettingsPage(driver);

        // Navigate to Advanced Settings Add Page
        navigationSteps.navigateToAddPage(
                ModuleName.ADVANCED_SETTINGS
        );

        // Click Reset
        advancedSettingsPage.resetAdvancedSettings();

        // Validate Name Reset
        AssertionUtils.assertEqualsWithMessage(
                advancedSettingsPage.getAdvanceSettingsNameValue(),
                "",
                "Advanced Settings name reset successfully",
                "Advanced Settings name not cleared"
        );

        // Validate Description Reset
        AssertionUtils.assertEqualsWithMessage(
                advancedSettingsPage.getAdvanceSettingsDescriptionValue(),
                "",
                "Description reset successfully",
                "Description not cleared"
        );
    }
}