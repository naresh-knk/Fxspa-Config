package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.ServiceCategoriesPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class ServiceCategoriesTest extends BaseTest {

    @Test
    public void verifyServiceCategoriesNavigation()
            throws InterruptedException {

        ServiceCategoriesPage serviceCategoriesPage =
                new ServiceCategoriesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(
                ModuleName.SERVICE_CATEGORY);

        // Create Service Category
        serviceCategoriesPage.createServiceCategories();

        // Capture Toast Message
        String actualToastMsg =
                serviceCategoriesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Service Category Successfully Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Service Category created successfully",
                "Service Category not created"
        );
    }

    @Test
    public void verifyresetfunctionality()
            throws InterruptedException {

        ServiceCategoriesPage serviceCategoriesPage =
                new ServiceCategoriesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(
                ModuleName.SERVICE_CATEGORY);

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