package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.CreateBudgetDetailsPage;
import com.idsnext.pages.EditBudgetDetailsPage;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.ServicesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditBudgetDetailsTest extends BaseTest {

    @Test
    public void verifyeditbudgetDetailsNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        EditBudgetDetailsPage editBudgetDetailsPage = new EditBudgetDetailsPage(driver);

        // Step 2: CLICK FX REPORTS     
        editBudgetDetailsPage.clickFXSPAConfigIcon();
        editBudgetDetailsPage.switchWindow();

        // Step 3: Open 3-dot menu
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

//     LoginPage loginPage = new LoginPage(driver);
//     loginPage.login(config.getUsername(), config.getPassword());

//     EditBudgetDetailsPage editBudgetDetailsPage = new EditBudgetDetailsPage(driver);
    
//     editBudgetDetailsPage.clickFXSPAConfigIcon();
//     editBudgetDetailsPage.switchWindow();

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