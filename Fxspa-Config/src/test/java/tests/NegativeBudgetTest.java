package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.NegativeBudgetPage;
import com.idsnext.pages.CreateBudgetDetailsPage;

import utils.BaseTest;

public class NegativeBudgetTest extends BaseTest {

    @Test
    public void verifyBudgetDetailsNegativeFlow() throws InterruptedException {

        CreateBudgetDetailsPage budgetDetailsPage =
                new CreateBudgetDetailsPage(driver);

        NegativeBudgetPage page =
                new NegativeBudgetPage(driver);

        // ===== NAVIGATION =====
        budgetDetailsPage.clickRandom();
        budgetDetailsPage.clickBudgetDetails();
        budgetDetailsPage.clickRandom();
        budgetDetailsPage.clickAdd();

        // ===== STEP 1 : Click Save Without Data =====

        page.clickSave();

        String toast1 = page.getToastMessage();

        System.out.println("Step 1 Toast : " + toast1);

        Assert.assertTrue(
                toast1.toLowerCase().contains("valid")
                        || toast1.toLowerCase().contains("required"),

                "Expected validation toast not shown"
        );

        // ===== STEP 2 : Trigger Field Validation =====

        page.triggerValidation();

        boolean validationVisible =
                page.isValidationDisplayed();

        String validationText =
                page.getAllValidationMessages();

        System.out.println("Step 2 Validation : " + validationText);

        Assert.assertTrue(
                validationVisible,
                "Validation messages not displayed"
        );

        Assert.assertTrue(
                validationText.toLowerCase().contains("required"),
                "Required field validation message not displayed"
        );
    }
}