package tests;


import org.testng.annotations.Test;

import com.idsnext.pages.EditServiceCategoriesPage;
import com.idsnext.pages.LoginPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditServiceCategoriesTest extends BaseTest {

    @Test
    public void verifyServiceCategoriesNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditServiceCategoriesPage editserviceCategoriesPage = new EditServiceCategoriesPage(driver);

        // Step 2: CLICK FX REPORTS     
        editserviceCategoriesPage.clickFXSPAConfigIcon();
        editserviceCategoriesPage.switchWindow();

        // Step 3: Open 3-dot menu
        editserviceCategoriesPage.clickRandom();
        editserviceCategoriesPage.clickServiceCategories();
        editserviceCategoriesPage.clickRandom();
        editserviceCategoriesPage.clickRow();
        editserviceCategoriesPage.editServiceCategories();

         // Step 4: Capture Toast Message
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

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditServiceCategoriesPage serviceCategoriesPage = new EditServiceCategoriesPage(driver);

        // Step 2: CLICK FX REPORTS     
        serviceCategoriesPage.clickFXSPAConfigIcon();
        serviceCategoriesPage.switchWindow();

        // Step 3: Open 3-dot menu
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