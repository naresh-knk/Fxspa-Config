package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.EditResourcesPage;
import com.idsnext.pages.ServicesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditResourcesTest extends BaseTest {

    @Test
    public void verifyResourcesNavigation() throws InterruptedException {

        EditResourcesPage editresourcesPage = new EditResourcesPage(driver);

        // Open 3-dot menu
        editresourcesPage.clickRandom();
        editresourcesPage.clickResources();
        editresourcesPage.clickRandom();
        editresourcesPage.clickRow();
        editresourcesPage.updateResources();

        //  Capture Toast Message
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

    public void verifyResetFunctionality() {

    EditResourcesPage editresourcesPage = new EditResourcesPage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);

    servicesPage.clickRandom();
    editresourcesPage.clickResources();
    servicesPage.clickRandom();
    editresourcesPage.clickRow();

    // Click Reset
    editresourcesPage.resetResources();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            editresourcesPage.getResourcesNameValue(),
            "",
            "Resources name reset successfully",
            "Resources name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            editresourcesPage.getResourcesDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );
}
    
}
