package tests;

import org.testng.annotations.Test;
import com.idsnext.enums.ModuleName;
import com.idsnext.pages.EditProductsPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditProductsTest extends BaseTest {

    @Test
    public void verifyProductsNavigation() throws InterruptedException {

        EditProductsPage editproductsPage =
                new EditProductsPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(ModuleName.PRODUCTS);

        // Open Edit Page
        editproductsPage.clickRow();

        // Update Product
        editproductsPage.editProducts();

        // Capture Toast Message
        String actualToastMsg =
                editproductsPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Product Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Product updated successfully",
                "Product not updated"
        );
    }

    @Test
    public void verifyResetFunctionality() {

        EditProductsPage editProductPage =
                new EditProductsPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(ModuleName.PRODUCTS);

        // Open Edit Page
        editProductPage.clickRow();

        // Click Reset
        editProductPage.resetProducts();

        // Assertions
        AssertionUtils.assertEqualsWithMessage(
                editProductPage.getProductNameValue(),
                "",
                "Product name reset successfully",
                "Product name not cleared"
        );

        AssertionUtils.assertEqualsWithMessage(
                editProductPage.getProductDescriptionValue(),
                "",
                "Description reset successfully",
                "Description not cleared"
        );
    }
}