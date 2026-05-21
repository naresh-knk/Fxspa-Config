package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.EditProductsPage;
import com.idsnext.pages.ServicesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditProductsTest extends BaseTest {

    @Test
    public void verifyProductsNavigation() throws InterruptedException {

         EditProductsPage editproductsPage = new EditProductsPage(driver);

        // Open 3-dot menu
        editproductsPage.clickRandom();
        editproductsPage.clickProducts();
        editproductsPage.clickRandom();
        editproductsPage.clickRow();
        editproductsPage.editProducts();

        //  Capture Toast Message
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

     @Test
public void verifyResetFunctionality() {


    EditProductsPage editProductPage = new EditProductsPage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);

    servicesPage.clickRandom();
    editProductPage.clickProducts();
    servicesPage.clickRandom();
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