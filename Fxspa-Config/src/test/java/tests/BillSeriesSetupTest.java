package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.BillSeriesSetupPage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class BillSeriesSetupTest extends BaseTest {

    @Test
    public void verifyBillSeriesNavigation()
            throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        BillSeriesSetupPage billSeriesSetupPage =
                new BillSeriesSetupPage(driver);

        // Navigate to Bill Series Setup Add Page
        navigationSteps.navigateToAddPage(
                ModuleName.BILL_SERIES
        );

        // Create Bill Series
        billSeriesSetupPage.createBillSeries();

        // Capture Toast Message
        String actualToastMsg =
                billSeriesSetupPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg =
                "Bill Saved Successfully!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Bill Saved successfully",
                "Bill not saved"
        );
    }

    @Test
    public void verifyresetfunctionality()
            throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        BillSeriesSetupPage billSeriesSetupPage =
                new BillSeriesSetupPage(driver);

        // Navigate to Bill Series Setup Add Page
        navigationSteps.navigateToAddPage(
                ModuleName.BILL_SERIES
        );

        // Click Reset
        billSeriesSetupPage.resetBillSeries();

        // Validate Outlet Reset
        AssertionUtils.assertEqualsWithMessage(
                billSeriesSetupPage.getOutletValue(),
                "",
                "Outlet name reset successfully",
                "Outlet name not cleared"
        );

        // Validate KOT Number Reset
        AssertionUtils.assertEqualsWithMessage(
                billSeriesSetupPage.getKOTNumberValue(),
                "",
                "KOT Number reset successfully",
                "KOT Number not cleared"
        );

        // Validate Bill Number Reset
        AssertionUtils.assertEqualsWithMessage(
                billSeriesSetupPage.getBillNumberValue(),
                "",
                "Bill Number reset successfully",
                "Bill Number not cleared"
        );
    }
}