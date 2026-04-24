package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.EditProductsPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditProductsTest extends BaseTest {

    @Test
    public void verifyProductsNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditProductsPage editproductsPage = new EditProductsPage(driver);

        // Step 2: CLICK FX SPA    
        editproductsPage.clickFXSPAConfigIcon();
        editproductsPage.switchWindow();

        // Step 3: Open 3-dot menu
        editproductsPage.clickRandom();
        editproductsPage.clickProducts();
        editproductsPage.clickRandom();
        editproductsPage.clickRow();
        editproductsPage.editProducts();

        // Step 4: Capture Toast Message
        String actualToastMsg = editproductsPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Product Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Product updated successfully",
                "Product not updated"
        );

    }
}