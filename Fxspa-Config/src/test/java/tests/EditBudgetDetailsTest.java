package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.EditBudgetDetailsPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditBudgetDetailsTest extends BaseTest {

    @Test
    public void verifyeditbudgetDetailsNavigation() throws InterruptedException {

        EditBudgetDetailsPage editBudgetDetailsPage = new EditBudgetDetailsPage(driver);

        editBudgetDetailsPage.clickRandom();
        editBudgetDetailsPage.clickBudgetDetails();
        editBudgetDetailsPage.clickRandom();
        editBudgetDetailsPage.clickRow();
        editBudgetDetailsPage.updateBudgetDetails();

          // Step 4: Capture Toast Message
        String actualToastMsg = editBudgetDetailsPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Budget Amount Successfully Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Budget Amount Updated successfully",
                "Budget Amount not updated"
        );

    }

//      @Test
//     public void verifyResetFunctionality() {

//     EditBudgetDetailsPage editBudgetDetailsPage = new EditBudgetDetailsPage(driver);

//     editBudgetDetailsPage.clickRandom();
//     editBudgetDetailsPage.clickBudgetDetails();
//     editBudgetDetailsPage.clickRandom();
//     editBudgetDetailsPage.clickRow();

//     // Click Reset
//     editBudgetDetailsPage.resetBudgetDetails();

//     // Assertions

//     AssertionUtils.assertEqualsWithMessage(
//             editBudgetDetailsPage.getBudgetDetailsNameValue(),
//             "",
//             "Budget Details name reset successfully",
//             "Budget Details name not cleared"
//     );

// }
}