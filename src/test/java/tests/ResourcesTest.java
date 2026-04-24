package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.ResourcesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class ResourcesTest extends BaseTest {

    @Test
    public void verifyResourcesNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
       loginPage.login(config.getUsername(), config.getPassword());

        ResourcesPage resourcesPage = new ResourcesPage(driver);

        // Step 2: CLICK FX REPORTS     
        resourcesPage.clickFXSPAConfigIcon();
        resourcesPage.switchWindow();

        // Step 3: Open 3-dot menu
        resourcesPage.clickRandom();
        resourcesPage.clickResources();
        resourcesPage.clickRandom();
        resourcesPage.clickAdd();
        resourcesPage.createResources();

        
        // Step 4: Capture Toast Message
        String actualToastMsg = resourcesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Resource Successfully Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Resources created successfully",
                "Resources not created"
        );

    }
}