package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.EditServiceCategoriesPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditServiceCategoriesTest extends BaseTest {

    @Test
    public void verifyServiceCategoriesNavigation() {

        EditServiceCategoriesPage editserviceCategoriesPage =
                new EditServiceCategoriesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(
                ModuleName.SERVICE_CATEGORY
        );

        // Open Edit Page
        editserviceCategoriesPage.clickRow();

        // Edit Service Category
        editserviceCategoriesPage.editServiceCategories();

        // Capture Toast Message
        String actualToastMsg =
                editserviceCategoriesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Service Category Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Service Category updated successfully",
                "Service Category not updated"
        );
    }

    @Test
    public void verifyresetfunctionality() {

        EditServiceCategoriesPage serviceCategoriesPage =
                new EditServiceCategoriesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(
                ModuleName.SERVICE_CATEGORY
        );

        // Open Edit Page
        serviceCategoriesPage.clickRow();

        // Click Reset
        serviceCategoriesPage.resetServiceCategories();

        // Assertions
        AssertionUtils.assertEqualsWithMessage(
                serviceCategoriesPage.getServiceNameValue(),
                "",
                "Service Categories name reset successfully",
                "Service Categories name not cleared"
        );

        AssertionUtils.assertEqualsWithMessage(
                serviceCategoriesPage.getServiceDescriptionValue(),
                "",
                "Description reset successfully",
                "Description not cleared"
        );

        AssertionUtils.assertEqualsWithMessage(
                serviceCategoriesPage.getServiceHSNCodeValue(),
                "",
                "HSN code reset successfully",
                "HSN code not cleared"
        );
    }
}