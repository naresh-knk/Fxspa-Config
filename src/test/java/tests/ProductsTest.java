package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.ProductsPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class ProductsTest extends BaseTest {

    @Test
    public void verifyProductsNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
       loginPage.login(config.getUsername(), config.getPassword());

        ProductsPage productsPage = new ProductsPage(driver);

        // Step 2: CLICK FX Spa     
        productsPage.clickFXSPAConfigIcon();
        productsPage.switchWindow();

        // Step 3: Open 3-dot menu
        productsPage.clickRandom();
        productsPage.clickProducts();
        productsPage.clickRandom();
        productsPage.clickAdd();
        productsPage.createProducts();

        String expectedProductName = productsPage.getCreatedProductName();
String actualProductName = productsPage.getFirstRowProductName();

AssertionUtils.assertEqualsWithMessage(
        actualProductName,
        expectedProductName,
        "Product created and visible in first row",
        "Product name mismatch in listing"
);


    }
}