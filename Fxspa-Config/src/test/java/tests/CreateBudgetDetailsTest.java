package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.CreateBudgetDetailsPage;
import com.idsnext.pages.ServicesPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class CreateBudgetDetailsTest extends BaseTest {

    @Test
    public void verifybudgetDetailsNavigation() throws InterruptedException {

        CreateBudgetDetailsPage budgetDetailsPage = new CreateBudgetDetailsPage(driver);

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

    
     @Test
    public void verifyResetFunctionality() {

    CreateBudgetDetailsPage BudgetDetailsPage = new CreateBudgetDetailsPage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);
    servicesPage.clickRandom();
    BudgetDetailsPage.clickBudgetDetails();
    servicesPage.clickRandom();
    BudgetDetailsPage.clickAdd();

    // Click Reset
    BudgetDetailsPage.resetBudgetDetails();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            BudgetDetailsPage.getBudgetDetailsNameValue(),
            "",
            "Budget Details name reset successfully",
            "Budget Details name not cleared"
    );

}
}