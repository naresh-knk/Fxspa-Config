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
}