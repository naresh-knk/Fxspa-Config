package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.NegativeServiceCategoryPage;
import com.idsnext.pages.ServiceCategoriesPage;

import utils.BaseTest;

public class NegativeServiceCategoryTest extends BaseTest {

    @Test
    public void verifyServiceCategoryNegativeFlow() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        ServiceCategoriesPage ServiceCategoryPage = new ServiceCategoriesPage(driver);
        NegativeServiceCategoryPage page = new NegativeServiceCategoryPage(driver);

        // ===== NAVIGATION =====
        ServiceCategoryPage.clickFXSPAConfigIcon();
        ServiceCategoryPage.switchWindow();
        ServiceCategoryPage.clickRandom();
        ServiceCategoryPage.clickServiceCategories();
        ServiceCategoryPage.clickRandom();
        ServiceCategoryPage.clickAdd();

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

        Assert.assertTrue(validationText.toLowerCase().contains("required"), "Service Category Name validation missing");
        Assert.assertTrue(validationText.toLowerCase().contains("required"), "Description validation missing");
        Assert.assertTrue(validationText.toLowerCase().contains("required"), "Description validation missing");
        
    }
}