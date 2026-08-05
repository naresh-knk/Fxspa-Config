package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.EditServicesPage;
import com.idsnext.pages.ServicesPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditServicesTest extends BaseTest {

    @Test
    public void verifyServicesNavigation() throws InterruptedException {

        EditServicesPage editservicesPage =
                new EditServicesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(ModuleName.SERVICES);

        // Update Service
        editservicesPage.clickRow();
        editservicesPage.updateServices();

        // Capture Toast Message
        String actualToastMsg =
                editservicesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Service Successfully Updated!";

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

        EditServicesPage editservicesPage =
                new EditServicesPage(driver);

        ServicesPage servicesPage =
                new ServicesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(ModuleName.SERVICES);

        // Open Edit Page
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