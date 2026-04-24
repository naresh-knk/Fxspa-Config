package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.EditResourcesPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditResourcesTest extends BaseTest {

    @Test
    public void verifyResourcesNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditResourcesPage editresourcesPage = new EditResourcesPage(driver);

        // Step 2: CLICK FX REPORTS     
        editresourcesPage.clickFXSPAConfigIcon();
        editresourcesPage.switchWindow();

        // Step 3: Open 3-dot menu
        editresourcesPage.clickRandom();
        editresourcesPage.clickResources();
        editresourcesPage.clickRandom();
        editresourcesPage.clickRow();
        editresourcesPage.updateResources();

        // Step 4: Capture Toast Message
        String actualToastMsg = editresourcesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Resource Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Resources updated successfully",
                "Resources not updated"
        );
    }
    
}
