package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.EditProductCategoriesPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditProductCategoriesTest extends BaseTest {

    @Test
    public void verifyProductCategoriesNavigation() throws InterruptedException {

        EditProductCategoriesPage editproductCategoriesPage = new EditProductCategoriesPage(driver);

        editproductCategoriesPage.clickRandom();
        editproductCategoriesPage.clickProductCategories();
        editproductCategoriesPage.clickRandom();
        editproductCategoriesPage.clickRow();
        editproductCategoriesPage.editProductCategories();

         //  Capture Toast Message
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


    @Test
    public void verifyresetfunctionality() throws InterruptedException {

        EditProductCategoriesPage editProductCategoriesPage = new EditProductCategoriesPage(driver);

        editProductCategoriesPage.clickRandom();
        editProductCategoriesPage.clickProductCategories();
        editProductCategoriesPage.clickRandom();
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