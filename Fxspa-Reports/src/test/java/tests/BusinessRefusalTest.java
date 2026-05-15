package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.BusinessRefusalPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class BusinessRefusalTest extends BaseTest {

    @Test
    public void verifyAppointmentReportNavigation() {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());
        BusinessRefusalPage reportPage = new BusinessRefusalPage(driver);

        reportPage.clickFXReport();
        reportPage.clickRandom();
        reportPage.clickReportSLabel();
        reportPage.clickSPAReport();
        reportPage.clickbRefusal();
        reportPage.clickGenerate();

         AssertionUtils.assertTrueWithMessage(
            reportPage.isBusinessRefusalReportGenerated(),           // condition
            "Audit Report generated successfully", // success message
            "Failed to generate Aduit Report"    // failure message
        );
    }
}
