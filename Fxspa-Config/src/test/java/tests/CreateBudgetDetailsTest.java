package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.CreateBudgetDetailsPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class CreateBudgetDetailsTest extends BaseTest {

    @Test
    public void verifybudgetDetailsNavigation() throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        CreateBudgetDetailsPage budgetDetailsPage =
                new CreateBudgetDetailsPage(driver);

        // Navigate to Budget Details Add Page
        navigationSteps.navigateToAddPage(
                ModuleName.BUDGET_DETAILS
        );

        // Create Budget Details
        budgetDetailsPage.createBudgetDetails();

        // Capture Toast Message
        String actualToastMsg =
                budgetDetailsPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Budget Amount Successfully Created!";

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

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        CreateBudgetDetailsPage budgetDetailsPage =
                new CreateBudgetDetailsPage(driver);

        // Navigate to Budget Details Add Page
        navigationSteps.navigateToAddPage(
                ModuleName.BUDGET_DETAILS
        );

        // Click Reset
        budgetDetailsPage.resetBudgetDetails();

        // Validate Reset
        AssertionUtils.assertEqualsWithMessage(
                budgetDetailsPage.getBudgetDetailsNameValue(),
                "",
                "Budget Details name reset successfully",
                "Budget Details name not cleared"
        );
    }
}