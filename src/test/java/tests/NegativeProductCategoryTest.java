package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.NegativeProductCategoryPage;
import com.idsnext.pages.ProductCategoriesPage;

import utils.BaseTest;

public class NegativeProductCategoryTest extends BaseTest {

    @Test
    public void verifyProductCategoryNegativeFlow() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        ProductCategoriesPage ProductCategoryPage = new ProductCategoriesPage(driver);
        NegativeProductCategoryPage page = new NegativeProductCategoryPage(driver);

        // ===== NAVIGATION =====
        ProductCategoryPage.clickFXSPAConfigIcon();
        ProductCategoryPage.switchWindow();
        ProductCategoryPage.clickRandom();
        ProductCategoryPage.clickProductCategories();
        ProductCategoryPage.clickRandom();
        ProductCategoryPage.clickAdd();

        // ===== STEP 1: Click Save → Check Toast =====
        page.clickSave();

        String toast1 = page.getToastMessage();
        System.out.println("Step1 Toast: " + toast1);

        Assert.assertTrue(
                toast1.toLowerCase().contains("valid") || toast1.toLowerCase().contains("required"),
                "Expected validation toast not shown"
        );

        // ===== STEP 2: Click fields without data → Check validation =====
        page.clickFieldsWithoutData();
        page.clickSave();

        boolean validationVisible = page.isValidationDisplayed();
        String validationText = page.getFieldErrors(); 

        System.out.println("Step2 Validation: " + validationText);

        Assert.assertTrue(validationVisible, "Validation messages not displayed");

        Assert.assertTrue(validationText.toLowerCase().contains("required"), "Product Category Name validation missing");
        Assert.assertTrue(validationText.toLowerCase().contains("required"), "Description validation missing");
        Assert.assertTrue(validationText.toLowerCase().contains("required"), "Description validation missing");
        
    }
}