package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.ServicesPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class ServicesTest extends BaseTest {

    @Test
    public void verifyServicesNavigation() {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(ModuleName.SERVICES);

        ServicesPage servicesPage =
                new ServicesPage(driver);

        // Create Service
        servicesPage.createServices();

        // Validation
        String expectedServiceName =
                servicesPage.getCreatedServiceName();

        String actualServiceName =
                servicesPage.getFirstRowServiceName();

        AssertionUtils.assertEqualsWithMessage(
                actualServiceName,
                expectedServiceName,
                "Service created and visible in first row",
                "Service name mismatch in listing"
        );
    }

    @Test
    public void verifyResetFunctionality() {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(ModuleName.SERVICES);

        ServicesPage servicesPage =
                new ServicesPage(driver);

        // Reset Service
        servicesPage.resetServices();

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