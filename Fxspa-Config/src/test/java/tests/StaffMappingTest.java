package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.StaffMappingPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class StaffMappingTest extends BaseTest {

    @Test
    public void verifyStaffMappingNavigation() throws InterruptedException {

        StaffMappingPage staffMappingPage = new StaffMappingPage(driver);

        //  Open 3-dot menu
        staffMappingPage.clickRandom();
        staffMappingPage.clickStaffMapping();
        staffMappingPage.clickRandom();
        staffMappingPage.clickAdd();
        staffMappingPage.createStaffMapping();

          //  Capture Toast Message
        String actualToastMsg = staffMappingPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Staff mapped to services successfully";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Staff mapped to services successfully",
                "Staff not mapped to services"
        );

    }
}
