package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.EditStaffMappingPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditStaffMappingTest extends BaseTest {

    @Test
    public void verifyStaffMappingNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditStaffMappingPage editstaffMappingPage = new EditStaffMappingPage(driver);

        // Step 2: CLICK FX REPORTS     
        editstaffMappingPage.clickFXSPAConfigIcon();
        editstaffMappingPage.switchWindow();

        // Step 3: Open 3-dot menu
        editstaffMappingPage.clickRandom();
        editstaffMappingPage.clickStaffMapping();
        editstaffMappingPage.clickRandom();
        editstaffMappingPage.clickRow();
        editstaffMappingPage.createStaffMapping();

          // Step 4: Capture Toast Message
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
