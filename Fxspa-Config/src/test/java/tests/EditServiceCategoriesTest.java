package tests;


import org.testng.annotations.Test;

import com.idsnext.pages.EditServiceCategoriesPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditServiceCategoriesTest extends BaseTest {

    @Test
    public void verifyServiceCategoriesNavigation() throws InterruptedException {

        EditServiceCategoriesPage editserviceCategoriesPage = new EditServiceCategoriesPage(driver);
        //  Open 3-dot menu
        editserviceCategoriesPage.clickRandom();
        editserviceCategoriesPage.clickServiceCategories();
        editserviceCategoriesPage.clickRandom();
        editserviceCategoriesPage.clickRow();
        editserviceCategoriesPage.editServiceCategories();

         //  Capture Toast Message
        String actualToastMsg = editserviceCategoriesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Service Category Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Service Category updated successfully",
                "Service Category not updated"
        );
    }

    @Test
    public void verifyresetfunctionality() throws InterruptedException {

        EditServiceCategoriesPage serviceCategoriesPage = new EditServiceCategoriesPage(driver);

        //  Open 3-dot menu
        serviceCategoriesPage.clickRandom();
        serviceCategoriesPage.clickServiceCategories();
        serviceCategoriesPage.clickRandom();
        serviceCategoriesPage.clickRow();
        // Click Reset
         serviceCategoriesPage.resetServiceCategories();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            serviceCategoriesPage.getServiceNameValue(),
            "",
            "Service Categories name reset successfully",
            "Service Categories name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            serviceCategoriesPage.getServiceDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            serviceCategoriesPage.getServiceHSNCodeValue(),
            "",
            "HSN code reset successfully",
            "HSN code not cleared"
    );

    }
}