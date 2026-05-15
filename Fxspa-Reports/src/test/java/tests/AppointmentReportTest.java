package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.AppointmentReportPage;
import com.idsnext.pages.LoginPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class AppointmentReportTest extends BaseTest {

    @Test
    public void verifyAppointmentReportNavigation() {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        AppointmentReportPage reportPage = new AppointmentReportPage(driver);

        // Step 2: CLICK FX REPORTS 
        reportPage.clickFXReport();

        // Step 3: Open 3-dot menu
        reportPage.clickRandom();

        // Step 4: Navigate Reports → SPA → Appointment
        reportPage.clickReportSLabel();
        reportPage.clickSPAReport();
        reportPage.clickAppoitmentReport();
        reportPage.clickCalender();
        reportPage.clickCalenderDate();
        reportPage.clickGenerate();

         AssertionUtils.assertTrueWithMessage(
            reportPage.isReportGenerated(),           // condition
            "Appointment Report generated successfully", // success message
            "Failed to generate Appointment Report"    // failure message
        );
    }
}
