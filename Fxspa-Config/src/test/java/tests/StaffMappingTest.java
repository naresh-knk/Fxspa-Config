package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.StaffMappingPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class StaffMappingTest extends BaseTest {

    @Test
    public void verifyStaffMappingNavigation() throws InterruptedException {

        StaffMappingPage staffMappingPage =
                new StaffMappingPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(
                ModuleName.STAFF_MAPPING);

        // Create Staff Mapping
        staffMappingPage.createStaffMapping();

        // Capture Toast Message
        String actualToastMsg =
                staffMappingPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Staff mapped to services successfully";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Staff mapped to services successfully",
                "Staff not mapped to services"
        );
    }
}