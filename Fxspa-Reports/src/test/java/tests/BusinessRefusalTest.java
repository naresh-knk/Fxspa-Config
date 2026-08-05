package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.BusinessRefusalPage;
import com.idsnext.pages.ReportButtonPage;
import com.idsnext.enums.ModuleName;
import com.idsnext.steps.ReportNavigationSteps;
import utils.AssertionUtils;
import utils.BaseTest;

public class BusinessRefusalTest extends BaseTest {

            private final String DOWNLOAD_PATH = "C:\\Users\\Deepshika\\Downloads";

    @Test
    public void verifyBusinessRefusalReportNavigation() {
        ReportNavigationSteps reportSteps = new ReportNavigationSteps(driver);
        BusinessRefusalPage reportPage = new BusinessRefusalPage(driver);

        reportSteps.navigateToSpaReport(ModuleName.BUSINESS_REFUSAL_REPORT);
        reportPage.clickOutlets();
        reportPage.closePopupByEscape();
        reportSteps.configureFiltersAndGenerate();

        AssertionUtils.assertTrueWithMessage(
            reportPage.isBusinessRefusalReportGenerated(),
            "Business Refusal Report generated successfully",
            "Failed to generate Business Refusal Report"
        );
    }

     @Test(priority = 2, dependsOnMethods = {"verifyBusinessRefusalReportNavigation"})
    public void verifyReportActionButtonsWorkflow() {
        
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
        buttonsPage.clickReset();
        AssertionUtils.assertTrueWithMessage(
                buttonsPage.isButtonReset(),
                "Reset button working successfully",
                "Reset button failed"
        );
    }
}