package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.ProductsPage;
import com.idsnext.pages.ServicesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class ProductsTest extends BaseTest {

    @Test
    public void verifyProductsNavigation() throws InterruptedException {

        ProductsPage productsPage = new ProductsPage(driver);

        // Open 3-dot menu
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

     @Test
        public void verifyResetFunctionality() {

    ProductsPage ProductPage = new ProductsPage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);

    servicesPage.clickRandom();
    ProductPage.clickProducts();
    servicesPage.clickRandom();
    ProductPage.clickAdd();

    // Click Reset
    ProductPage.resetProducts();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            ProductPage.getProductNameValue(),
            "",
            "Product name reset successfully",
            "Product name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            ProductPage.getProductDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );
}

}