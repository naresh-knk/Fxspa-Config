package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.ResourcesPage;
import com.idsnext.pages.ServicesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class ResourcesTest extends BaseTest {

    @Test
    public void verifyResourcesNavigation() throws InterruptedException {

        ResourcesPage resourcesPage = new ResourcesPage(driver);

        // Open 3-dot menu
        resourcesPage.clickRandom();
        resourcesPage.clickResources();
        resourcesPage.clickRandom();
        resourcesPage.clickAdd();
        resourcesPage.createResources();

        
        //  Capture Toast Message
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

   @Test
public void verifyResetFunctionality() {

    ResourcesPage resourcesPage = new ResourcesPage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);

    servicesPage.clickRandom();
    resourcesPage.clickResources();
    servicesPage.clickRandom();
    resourcesPage.clickAdd();

    // Click Reset
    resourcesPage.resetResources();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            resourcesPage.getResourcesNameValue(),
            "",
            "Resources name reset successfully",
            "Resources name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            resourcesPage.getResourcesDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );
}
}