package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.NegativeProductPage;
import com.idsnext.pages.ProductsPage;

import utils.BaseTest;

public class NegativeProductTest extends BaseTest {

    @Test
    public void verifyProductNegativeFlow() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        ProductsPage productsPage = new ProductsPage(driver);
        NegativeProductPage page = new NegativeProductPage(driver);

        // ===== NAVIGATION =====
        productsPage.clickFXSPAConfigIcon();
        productsPage.switchWindow();
        productsPage.clickRandom();
        productsPage.clickProducts();
        productsPage.clickRandom();
        productsPage.clickAdd();

        // ===== STEP 1: Click Save → Check Toast =====
        page.clickSave();

        String toast1 = page.getToastMessage();
        System.out.println("Step1 Toast: " + toast1);

        Assert.assertTrue(
                toast1.toLowerCase().contains("valid") || toast1.toLowerCase().contains("required"),
                "Expected validation toast not shown"
        );

        // ===== STEP 2: Click fields without data → Check validation =====
        page.clickFirstThreeFieldsWithoutData();
        page.clickSave();

        boolean validationVisible = page.isValidationDisplayed();
        String validationText = page.getFirstThreeFieldErrors(); 

        System.out.println("Step2 Validation: " + validationText);

        Assert.assertTrue(validationVisible, "Validation messages not displayed");

        Assert.assertTrue(validationText.toLowerCase().contains("required"), "Product Name validation missing");
        Assert.assertTrue(validationText.toLowerCase().contains("required"), "Description validation missing");
        // ===== STEP 3: Upload invalid file =====
        page.uploadInvalidFile("C:\\Users\\Deepshika\\Desktop\\IdsNext Automation\\idsnext-automation\\src\\resources\\testdata\\sample.pdf");
        Thread.sleep(2000);
        String toast2 = page.getToastMsgError();
        System.out.println("Step3 Toast (Invalid File): " + toast2);

        Assert.assertTrue(
                toast2.toLowerCase().contains("invalid") || toast2.toLowerCase().contains("format"),
                "Invalid file upload toast not shown"
        );
    }
}