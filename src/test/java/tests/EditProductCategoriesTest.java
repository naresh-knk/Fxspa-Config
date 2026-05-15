package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.EditProductCategoriesPage;
import com.idsnext.pages.LoginPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditProductCategoriesTest extends BaseTest {

    @Test
    public void verifyProductCategoriesNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditProductCategoriesPage editproductCategoriesPage = new EditProductCategoriesPage(driver);

        // Step 2: CLICK FX REPORTS     
        editproductCategoriesPage.clickFXSPAConfigIcon();
        editproductCategoriesPage.switchWindow();

        // Step 3: Open 3-dot menu
        editproductCategoriesPage.clickRandom();
        editproductCategoriesPage.clickProductCategories();
        editproductCategoriesPage.clickRandom();
        editproductCategoriesPage.clickRow();
        editproductCategoriesPage.editProductCategories();

         // Step 4: Capture Toast Message
        String actualToastMsg = editproductCategoriesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Product Category Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Product Category updated successfully",
                "Product Category not updated"
        );

}


    @Test
    public void verifyresetfunctionality() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditProductCategoriesPage editProductCategoriesPage = new EditProductCategoriesPage(driver);

        // Step 2: CLICK FX REPORTS     
        editProductCategoriesPage.clickFXSPAConfigIcon();
        editProductCategoriesPage.switchWindow();

        // Step 3: Open 3-dot menu
        editProductCategoriesPage.clickRandom();
        editProductCategoriesPage.clickProductCategories();
        editProductCategoriesPage.clickRandom();
        editProductCategoriesPage.clickRow();
        // Click Reset
    editProductCategoriesPage.resetProductCategories();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            editProductCategoriesPage.getProductNameValue(),
            "",
            "Product Categories name reset successfully",
            "Product Categories name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            editProductCategoriesPage.getProductDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            editProductCategoriesPage.getProductHSNCodeValue(),
            "",
            "HSN code reset successfully",
            "HSN code not cleared"
    );

    }
}