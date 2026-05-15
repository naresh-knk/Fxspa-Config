package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.AuditorsPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class AuditorsTest extends BaseTest {

    @Test
    public void verifyAuditorsReportNavigation() {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());
        AuditorsPage reportPage = new AuditorsPage(driver);

        // Step 2: CLICK FX REPORTS 
        reportPage.clickFXReport();

        // Step 3: Open 3-dot menu
        reportPage.clickRandom();

        // Step 4: Navigate Reports → SPA → Appointment
        reportPage.clickReportSLabel();
        reportPage.clickSPAReport();
        reportPage.clickAuditorReport();
        reportPage.clickCalender();
        reportPage.clickCalenderDate();
        reportPage.clickGenerate();

        AssertionUtils.assertTrueWithMessage(
            reportPage.isAuditReportGenerated(),           // condition
            "Audit Report generated successfully", // success message
            "Failed to generate Aduit Report"    // failure message
        );

    }
}
