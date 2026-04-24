package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.ProductCategoriesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class ProductCategoriesTest extends BaseTest {

    @Test
    public void verifyProductCategoriesNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        ProductCategoriesPage productCategoriesPage = new ProductCategoriesPage(driver);

        // Step 2: CLICK FX REPORTS     
        productCategoriesPage.clickFXSPAConfigIcon();
        productCategoriesPage.switchWindow();

        // Step 3: Open 3-dot menu
        productCategoriesPage.clickRandom();
        productCategoriesPage.clickProductCategories();
        productCategoriesPage.clickRandom();
        productCategoriesPage.clickAdd();
        productCategoriesPage.createProductCategories();

        // Step 4: Capture Toast Message
        String actualToastMsg = productCategoriesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Product Category Successfully Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Product Category created successfully",
                "Product Category not created"
        );

    }
}