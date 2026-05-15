package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.DashboardPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class DashboardTest extends BaseTest {

    @Test
    public void verifyAllDashboardReports() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(config.getUsername(), config.getPassword());

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.clickFXOperations();

        dashboardPage.switchToLatestTab();

        dashboardPage.waitDashboardLoaded();

        // ===== Appointment Report =====

        if (dashboardPage.clickLastMonth("Appointments")
                && dashboardPage.clickBreakupView("Appointments")) {

            dashboardPage.clickGenerate();

            AssertionUtils.assertTrueWithMessage(
                    dashboardPage.isReportGenerated(),
                    "Appointment Report generated successfully",
                    "Failed to generate Appointment Report"
            );

            dashboardPage.clickClearButton();

        } else {

            System.out.println("Appointment Record Not Generated");
        }

        // ===== Sales Report =====

        if (dashboardPage.clickLastMonth("Sales")
                && dashboardPage.clickBreakupView("Sales")) {

            dashboardPage.clickGenerate();

            AssertionUtils.assertTrueWithMessage(
                    dashboardPage.isReportGenerated(),
                    "Sales Report generated successfully",
                    "Failed to generate Sales Report"
            );

            dashboardPage.clickClearButton();

        } else {

            System.out.println("Sales Record Not Generated");
        }

        // ===== Top Selling Report =====

        if (dashboardPage.clickLastMonth("Top Selling")
                && dashboardPage.clickBreakupView("Top Selling")) {

            dashboardPage.clickGenerate();

            AssertionUtils.assertTrueWithMessage(
                    dashboardPage.isReportGenerated(),
                    "Top Selling Report generated successfully",
                    "Failed to generate Top Selling Report"
            );

            dashboardPage.clickClearButton();

        } else {

            System.out.println("Top Selling Record Not Generated");
        }

        // ===== Staff Utilization Report =====

        // if (dashboardPage.clickLastMonth("Staff Utilization")
        //         && dashboardPage.clickBreakupView("Staff Utilization")) {

        //     dashboardPage.clickGenerate();

        //     AssertionUtils.assertTrueWithMessage(
        //             dashboardPage.isReportGenerated(),
        //             "Staff Utilization Report generated successfully",
        //             "Failed to generate Staff Utilization Report"
        //     );

        //     dashboardPage.clickClearButton();

        // } else {

        //     System.out.println("Staff Utilization Record Not Generated");
        // }

        // ===== Room Utilization Report =====

        // if (dashboardPage.clickLastMonth("Room Utilization")
        //         && dashboardPage.clickBreakupView("Room Utilization")) {

        //     dashboardPage.clickGenerate();

        //     AssertionUtils.assertTrueWithMessage(
        //             dashboardPage.isReportGenerated(),
        //             "Room Utilization Report generated successfully",
        //             "Failed to generate Room Utilization Report"
        //     );

        //     dashboardPage.clickClearButton();

        // } else {

        //     System.out.println("Room Utilization Record Not Generated");
        // }

        // ===== Business Refusal Report =====

        // if (dashboardPage.clickLastMonth("Business Refusal")
        //         && dashboardPage.clickBreakupView("Business Refusal")) {

        //     dashboardPage.clickGenerate();

        //     AssertionUtils.assertTrueWithMessage(
        //             dashboardPage.isReportGenerated(),
        //             "Business Refusal Report generated successfully",
        //             "Failed to generate Business Refusal Report"
        //     );

        //     dashboardPage.clickClearButton();

        // } else {

        //     System.out.println("Business Refusal Record Not Generated");
        // }

        // ===== Complimentary Report =====

        if (dashboardPage.clickLastMonth("Complimentary")
                && dashboardPage.clickBreakupView("Complimentary")) {

            dashboardPage.clickGenerate();

            AssertionUtils.assertTrueWithMessage(
                    dashboardPage.isReportGenerated(),
                    "Complimentary Report generated successfully",
                    "Failed to generate Complimentary Report"
            );

            dashboardPage.clickClearButton();

        } else {

            System.out.println("Complimentary Record Not Generated");
        }

        // ===== Resident and Non-Resident Split Report =====

        if (dashboardPage.clickLastMonth("Resident")
                && dashboardPage.clickBreakupView("Resident")) {

            dashboardPage.clickGenerate();

            AssertionUtils.assertTrueWithMessage(
                    dashboardPage.isReportGenerated(),
                    "Resident and Non-Resident Split Report generated successfully",
                    "Failed to generate Resident and Non-Resident Split Report"
            );

            dashboardPage.clickClearButton();

        } else {

            System.out.println("Resident Record Not Generated");
        }
    }
}