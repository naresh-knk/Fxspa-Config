package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.ProductCategoriesPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class ProductCategoriesTest extends BaseTest {

    @Test
    public void verifyProductCategoriesNavigation() throws InterruptedException {

        ProductCategoriesPage productCategoriesPage = new ProductCategoriesPage(driver);

        // Open 3-dot menu
        productCategoriesPage.clickRandom();
        productCategoriesPage.clickProductCategories();
        productCategoriesPage.clickRandom();
        productCategoriesPage.clickAdd();
        productCategoriesPage.createProductCategories();

        //  Capture Toast Message
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

    @Test
    public void verifyresetfunctionality() throws InterruptedException {

        ProductCategoriesPage ProductCategoriesPage = new ProductCategoriesPage(driver);

        //  Open 3-dot menu
        ProductCategoriesPage.clickRandom();
        ProductCategoriesPage.clickProductCategories();
        ProductCategoriesPage.clickRandom();
        ProductCategoriesPage.clickAdd();
        // Click Reset
        ProductCategoriesPage.resetProductCategories();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            ProductCategoriesPage.getProductNameValue(),
            "",
            "Product Categories name reset successfully",
            "Product Categories name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            ProductCategoriesPage.getProductDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            ProductCategoriesPage.getProductHSNCodeValue(),
            "",
            "HSN code reset successfully",
            "HSN code not cleared"
    );

    }
}