package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.MasterExportReportPage;
import com.idsnext.pages.ReportButtonPage;
import com.idsnext.enums.ModuleName;
import com.idsnext.steps.ReportNavigationSteps;
import utils.AssertionUtils;
import utils.BaseTest;

public class MasterExportReportTest extends BaseTest {

        private final String DOWNLOAD_PATH = "C:\\Users\\Deepshika\\Downloads";
        

    @Test
    public void verifyMasterExportReportNavigation() {
        ReportNavigationSteps reportSteps = new ReportNavigationSteps(driver);
        MasterExportReportPage reportPage = new MasterExportReportPage(driver);

        reportSteps.navigateToSpaReport(ModuleName.MASTER_EXPORT_REPORT);
        reportPage.clickOutlets();
        reportSteps.generate();

        AssertionUtils.assertTrueWithMessage(
            reportPage.isMasterExportReportGenerated(),
            "Master Export Report generated successfully",
            "Failed to generate Master Export Report"
        );
    }

    @Test(priority = 2, dependsOnMethods = {"verifyMasterExportReportNavigation"})
    public void verifyReportActionButtonsWorkflow() {
        ReportNavigationSteps reportSteps = new ReportNavigationSteps(driver);
        ReportButtonPage buttonsPage = new ReportButtonPage(driver);

        // 1. Export to PDF
        buttonsPage.clickExportPdf();
        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isPdfDownloaded(DOWNLOAD_PATH),
                "PDF downloaded successfully",
                "PDF download failed"
        );

        // 2. Export to Excel
        buttonsPage.clickExportExcel();
        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isExcelDownloaded(DOWNLOAD_PATH),
                "Excel downloaded successfully",
                "Excel download failed"
        );

        // 3. Print
        buttonsPage.clickPrint();

        // 4. Email
        buttonsPage.clickEmail();

        // 5. Back Button
        buttonsPage.clickBack();
        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isReportVisible(),
                "Back button working successfully",
                "Back button failed"
        );

        // 6. Reset Button
        reportSteps.generate();
        buttonsPage.clickReset();
        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isReportVisible(),
                "Reset button working successfully",
                "Reset button failed"
        );
    }
}