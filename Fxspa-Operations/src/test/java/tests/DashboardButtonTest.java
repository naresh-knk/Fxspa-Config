package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.pages.DashboardButtonPage;
import com.idsnext.pages.DashboardPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class DashboardButtonTest extends BaseTest {


        DashboardPage dashboardPage;
        DashboardButtonPage buttonsPage;

@BeforeMethod
public void setup() {

    LoginPage loginPage = new LoginPage(driver);

    loginPage.login(
            config.getUsername(),
            config.getPassword()
    );

    dashboardPage = new DashboardPage(driver);

    buttonsPage = new DashboardButtonPage(driver);

    dashboardPage.clickFXOperations();

    dashboardPage.switchToLatestTab();

    dashboardPage.waitDashboardLoaded();
}


    @Test
    public void verifyAppointmentButtons() {

        dashboardPage.clickLastMonth("Appointment");

        dashboardPage.clickBreakupView("Appointment");

    }

    @Test
    public void verifySalesButtons() {

        dashboardPage.clickLastMonth("Sales");

        dashboardPage.clickBreakupView("Sales");

    }

    @Test
    public void verifyTopSellingButtons() {

        dashboardPage.clickLastMonth("Top Selling");

        dashboardPage.clickBreakupView("Top Selling");

    }

   // @Test
    public void verifyRoomUtilizationButtons() {

        dashboardPage.clickLastMonth("Room Utilization");

        dashboardPage.clickBreakupView("Room Utilization");

    }

    // @Test
    public void verifyBusinessRefusalButtons() {

        dashboardPage.clickLastMonth("Business Refusal");

        dashboardPage.clickBreakupView("Business Refusal");

    }

     @Test
    public void verifyComplimentaryButtons() {

        dashboardPage.clickLastMonth("Complimentary");

        dashboardPage.clickBreakupView("Complimentary");

    }

    @Test
    public void verifyResidentButtons() {

        dashboardPage.clickLastMonth("Resident and Non-Resident Split");

        dashboardPage.clickBreakupView("Resident and Non-Resident Split");

    }

    @AfterMethod

    public void teardown(){
        // ===== GENERATE =====

        buttonsPage.clickGenerate();

        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isReportVisible(),
                "Report generated successfully",
                "Report generation failed"
        );

        // ===== BACK BUTTON =====

        buttonsPage.clickBack();

        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isReportVisible(),
                "Back button working successfully",
                "Back button failed"
        );

        // ===== GENERATE AGAIN =====

        buttonsPage.clickGenerate();

        // ===== EXPORT PDF =====

        buttonsPage.clickExportPdf();

        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isPdfDownloaded("C:\\Users\\Deepshika\\Downloads"),
                "PDF downloaded successfully",
                "PDF download failed"
        );

        // ===== EXPORT EXCEL =====

        buttonsPage.clickExportExcel();

        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isExcelDownloaded("C:\\Users\\Deepshika\\Downloads"),
                "Excel downloaded successfully",
                "Excel download failed"
        );

        // ===== RESET BUTTON =====

        buttonsPage.clickReset();

        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isReportVisible(),
                "Reset button working successfully",
                "Reset button failed"
        );

        // ===== Not resetting the start date field =====

        // AssertionUtils.assertTrueWithMessage(
        //         buttonsPage.isStartAndEndDateSame(),
        //         "Start date and End date are same after reset",
        //         "Start date and End date are not same after reset"
        // );
    }
}

