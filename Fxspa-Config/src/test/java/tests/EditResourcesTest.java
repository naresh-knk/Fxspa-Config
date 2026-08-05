package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.EditResourcesPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditResourcesTest extends BaseTest {

    @Test
    public void verifyResourcesNavigation() throws InterruptedException {

        EditResourcesPage editresourcesPage =
                new EditResourcesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(ModuleName.RESOURCES);

        // Open Edit Page
        editresourcesPage.clickRow();

        // Update Resource
        editresourcesPage.updateResources();

        // Capture Toast Message
        String actualToastMsg =
                editresourcesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Resource Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Resources updated successfully",
                "Resources not updated"
        );
    }

    @Test
    public void verifyResetFunctionality() {

        EditResourcesPage editresourcesPage =
                new EditResourcesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(ModuleName.RESOURCES);

        // Open Edit Page
        editresourcesPage.clickRow();

        // Click Reset
        editresourcesPage.resetResources();

        // Assertions
        AssertionUtils.assertEqualsWithMessage(
                editresourcesPage.getResourcesNameValue(),
                "",
                "Resources name reset successfully",
                "Resources name not cleared"
        );

        AssertionUtils.assertEqualsWithMessage(
                editresourcesPage.getResourcesDescriptionValue(),
                "",
                "Description reset successfully",
                "Description not cleared"
        );
    }
}