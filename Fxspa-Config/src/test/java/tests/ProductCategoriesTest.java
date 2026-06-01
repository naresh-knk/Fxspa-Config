package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.ProductCategoriesPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class ProductCategoriesTest extends BaseTest {

    @Test
    public void verifyProductCategoriesNavigation() {

        ProductCategoriesPage productCategoriesPage =
                new ProductCategoriesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(
                ModuleName.PRODUCT_CATEGORY
        );

        // Create Product Category
        productCategoriesPage.createProductCategories();

        // Capture Toast Message
        String actualToastMsg =
                productCategoriesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Product Category Successfully Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Product Category created successfully",
                "Product Category not created"
        );
    }

    @Test
    public void verifyresetfunctionality() {

        ProductCategoriesPage productCategoriesPage =
                new ProductCategoriesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToAddPage(
                ModuleName.PRODUCT_CATEGORY
        );

        // Click Reset
        productCategoriesPage.resetProductCategories();

        // Assertions
        AssertionUtils.assertEqualsWithMessage(
                productCategoriesPage.getProductNameValue(),
                "",
                "Product Categories name reset successfully",
                "Product Categories name not cleared"
        );

        AssertionUtils.assertEqualsWithMessage(
                productCategoriesPage.getProductDescriptionValue(),
                "",
                "Description reset successfully",
                "Description not cleared"
        );

        AssertionUtils.assertEqualsWithMessage(
                productCategoriesPage.getProductHSNCodeValue(),
                "",
                "HSN code reset successfully",
                "HSN code not cleared"
        );
    }
}