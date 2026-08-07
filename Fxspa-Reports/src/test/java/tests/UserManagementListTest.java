package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.UserManagementListPage;
import com.idsnext.pages.ReportButtonPage;
import com.idsnext.enums.ModuleName;
import com.idsnext.steps.ReportNavigationSteps;
import utils.AssertionUtils;
import utils.BaseTest;

public class UserManagementListTest extends BaseTest {

        private final String DOWNLOAD_PATH = "C:\\Users\\Deepshika\\Downloads";

    @Test
    public void verifyUserManagementListNavigation() {
        ReportNavigationSteps reportSteps = new ReportNavigationSteps(driver);
        UserManagementListPage reportPage = new UserManagementListPage(driver);

        reportSteps.navigateToSpaReport(ModuleName.USER_MANAGEMENT_LIST_REPORT);
        reportSteps.configureFiltersAndGenerate();

        AssertionUtils.assertTrueWithMessage(
            reportPage.isUserManagementListGenerated(),
            "User Management List  generated successfully",
            "Failed to generate User Management List"
        );
    }

     @Test(priority = 2, dependsOnMethods = {"verifyUserManagementListNavigation"})
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