package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.EditProductCategoriesPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditProductCategoriesTest extends BaseTest {

    @Test
    public void verifyProductCategoriesNavigation() {

        EditProductCategoriesPage editproductCategoriesPage =
                new EditProductCategoriesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(
                ModuleName.PRODUCT_CATEGORY
        );

        // Open Edit Page
        editproductCategoriesPage.clickRow();

        // Edit Product Category
        editproductCategoriesPage.editProductCategories();

        // Capture Toast Message
        String actualToastMsg =
                editproductCategoriesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Product Category Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Product Category updated successfully",
                "Product Category not updated"
        );
    }

    @Test
    public void verifyresetfunctionality() {

        EditProductCategoriesPage editProductCategoriesPage =
                new EditProductCategoriesPage(driver);

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        // Generic Navigation
        navigationSteps.navigateToModule(
                ModuleName.PRODUCT_CATEGORY
        );

        // Open Edit Page
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