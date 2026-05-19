package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.BillPrintAuditPage;
import com.idsnext.pages.LoginPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class BillPrintAuditTest extends BaseTest {

    @Test
    public void verifyBillPrintAuditReportNavigation() {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());
        BillPrintAuditPage reportPage = new BillPrintAuditPage(driver);

        reportPage.clickFXReport();
        reportPage.clickRandom();
        reportPage.clickReportSLabel();
        reportPage.clickSPAReport();
        reportPage.clickbRefusal();
        reportPage.clickOutlets();
        reportPage.clickCalender();
        reportPage.clickCalenderDate();
        reportPage.clickGenerate();

         AssertionUtils.assertTrueWithMessage(
            reportPage.isBillPrintAuditReportGenerated(),           // condition
            "Bill Print Audit Report generated successfully", // success message
            "Failed to generate Bill Print Aduit Report"    // failure message
        );
    }
}
