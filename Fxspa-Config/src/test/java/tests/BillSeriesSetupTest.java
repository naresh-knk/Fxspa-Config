package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.BillSeriesSetupPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class BillSeriesSetupTest extends BaseTest {

    @Test
    public void verifyBillSeriesNavigation() throws InterruptedException {

        BillSeriesSetupPage billSeriesSetupPage = new BillSeriesSetupPage(driver);
        billSeriesSetupPage.clickRandom();
        billSeriesSetupPage.clickBillSeries();
        billSeriesSetupPage.clickRandom();
        billSeriesSetupPage.clickAdd();
        billSeriesSetupPage.createBillSeries();

          
        String actualToastMsg = billSeriesSetupPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Bill Saved Successfully!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Bill Saved successfully",
                "Bill not saved"
        );

    }

     @Test
    public void verifyresetfunctionality() throws InterruptedException {

        BillSeriesSetupPage billSeriesSetupPage = new BillSeriesSetupPage(driver);

        billSeriesSetupPage.clickRandom();
        billSeriesSetupPage.clickBillSeries();
        billSeriesSetupPage.clickRandom();
        billSeriesSetupPage.clickAdd();
        // Click Reset
    billSeriesSetupPage.resetBillSeries();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            billSeriesSetupPage.getOutletValue(),
            "",
            "Outlet name reset successfully",
            "Outlet name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            billSeriesSetupPage.getKOTNumberValue(),
            "",
            "KOT Number reset successfully",
            "KOT Number not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            billSeriesSetupPage.getBillNumberValue(),
            "",
            "Bill Number reset successfully",
            "Bill Number not cleared"
    );

    }
}