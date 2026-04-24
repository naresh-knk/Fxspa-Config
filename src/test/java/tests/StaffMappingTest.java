package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.StaffMappingPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class StaffMappingTest extends BaseTest {

    @Test
    public void verifyStaffMappingNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        StaffMappingPage staffMappingPage = new StaffMappingPage(driver);

        // Step 2: CLICK FX REPORTS     
        staffMappingPage.clickFXSPAConfigIcon();
        staffMappingPage.switchWindow();

        // Step 3: Open 3-dot menu
        staffMappingPage.clickRandom();
        staffMappingPage.clickStaffMapping();
        staffMappingPage.clickRandom();
        staffMappingPage.clickAdd();
        staffMappingPage.createStaffMapping();

          // Step 4: Capture Toast Message
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
