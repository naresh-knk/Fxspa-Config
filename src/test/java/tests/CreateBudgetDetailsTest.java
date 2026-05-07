package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.CreateBudgetDetailsPage;
import com.idsnext.pages.LoginPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class CreateBudgetDetailsTest extends BaseTest {

    @Test
    public void verifybudgetDetailsNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        CreateBudgetDetailsPage budgetDetailsPage = new CreateBudgetDetailsPage(driver);

        // Step 2: CLICK FX REPORTS     
        budgetDetailsPage.clickFXSPAConfigIcon();
        budgetDetailsPage.switchWindow();

        // Step 3: Open 3-dot menu
        budgetDetailsPage.clickRandom();
        budgetDetailsPage.clickBudgetDetails();
        budgetDetailsPage.clickRandom();
        budgetDetailsPage.clickAdd();
        budgetDetailsPage.createBudgetDetails();

          // Step 4: Capture Toast Message
        String actualToastMsg = budgetDetailsPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Budget Amount Successfully Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Budget Amount created successfully",
                "Budget Amount not created"
        );

    }
}