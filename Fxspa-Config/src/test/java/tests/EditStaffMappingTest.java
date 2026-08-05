package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.EditStaffMappingPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditStaffMappingTest extends BaseTest {

    @Test
    public void verifyStaffMappingNavigation() throws InterruptedException {

        EditStaffMappingPage editstaffMappingPage = new EditStaffMappingPage(driver);

        editstaffMappingPage.clickRandom();
        editstaffMappingPage.clickStaffMapping();
        editstaffMappingPage.clickRandom();
        editstaffMappingPage.clickRow();
        editstaffMappingPage.createStaffMapping();

          //  Capture Toast Message
        String actualToastMsg = editstaffMappingPage.getToastMsg();

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
