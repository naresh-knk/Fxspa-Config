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
}