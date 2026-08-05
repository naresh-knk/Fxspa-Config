package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.ProductsPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class ProductsTest extends BaseTest {

    @Test
    public void verifyProductsNavigation() throws InterruptedException {

        ProductsPage productsPage =
                new ProductsPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(ModuleName.PRODUCTS);

        // Create Product
        productsPage.createProducts();

        // Validation
        String expectedProductName =
                productsPage.getCreatedProductName();

        String actualProductName =
                productsPage.getFirstRowProductName();

        AssertionUtils.assertEqualsWithMessage(
                actualProductName,
                expectedProductName,
                "Product created and visible in first row",
                "Product name mismatch in listing"
        );
    }

    @Test
    public void verifyResetFunctionality() {

        ProductsPage productsPage =
                new ProductsPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(ModuleName.PRODUCTS);

        // Click Reset
        productsPage.resetProducts();

        // Assertions
        AssertionUtils.assertEqualsWithMessage(
                productsPage.getProductNameValue(),
                "",
                "Product name reset successfully",
                "Product name not cleared"
        );

        AssertionUtils.assertEqualsWithMessage(
                productsPage.getProductDescriptionValue(),
                "",
                "Description reset successfully",
                "Description not cleared"
        );
    }
}