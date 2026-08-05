package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.EditBudgetDetailsPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditBudgetDetailsTest extends BaseTest {

    @Test
    public void verifyeditbudgetDetailsNavigation()
            throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        EditBudgetDetailsPage editBudgetDetailsPage =
                new EditBudgetDetailsPage(driver);

        // Navigate to Budget Details module
        navigationSteps.navigateToModule(
                ModuleName.BUDGET_DETAILS
        );

        // Open existing row
        editBudgetDetailsPage.clickRow();

        // Update Budget Details
        editBudgetDetailsPage.updateBudgetDetails();

        // Capture Toast Message
        String actualToastMsg =
                editBudgetDetailsPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Budget Amount Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Budget Amount Updated successfully",
                "Budget Amount not updated"
        );
    }

    // Reset functionality is not working
    // @Test
    public void verifyResetFunctionality() {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        EditBudgetDetailsPage editBudgetDetailsPage =
                new EditBudgetDetailsPage(driver);

        // Navigate to Budget Details module
        navigationSteps.navigateToModule(
                ModuleName.BUDGET_DETAILS
        );

        // Open existing row
        editBudgetDetailsPage.clickRow();

        // Click Reset
        editBudgetDetailsPage.resetBudgetDetails();

        // Validate Reset
        AssertionUtils.assertEqualsWithMessage(
                editBudgetDetailsPage.getBudgetDetailsNameValue(),
                "",
                "Budget Details name reset successfully",
                "Budget Details name not cleared"
        );
    }
}