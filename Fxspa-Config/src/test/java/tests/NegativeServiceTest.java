package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.NegativeServicePage;
import com.idsnext.steps.NavigationSteps;

import utils.BaseTest;

public class NegativeServiceTest extends BaseTest {

    @Test
    public void verifyServiceNegativeFlow()
            throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        NegativeServicePage page =
                new NegativeServicePage(driver);

        // ===== NAVIGATION =====
        navigationSteps.navigateToAddPage(
                ModuleName.SERVICES
        );

        // ===== STEP 1 : Click Save =====
        page.clickSave();

        String toast1 =
                page.getToastMessage();

        System.out.println(
                "Step1 Toast: " + toast1
        );

        Assert.assertTrue(
                toast1.toLowerCase().contains("valid")
                        || toast1.toLowerCase().contains("required"),
                "Expected validation toast not shown"
        );

        // ===== STEP 2 : Empty Field Validation =====
        page.clickFirstThreeFieldsWithoutData();

        page.clickSave();

        boolean validationVisible =
                page.isValidationDisplayed();

        String validationText =
                page.getFirstThreeFieldErrors();

        System.out.println(
                "Step2 Validation: "
                        + validationText
        );

        Assert.assertTrue(
                validationVisible,
                "Validation messages not displayed"
        );

        Assert.assertTrue(
                validationText.toLowerCase().contains(
                        "required"
                ),
                "Service Name validation missing"
        );

        Assert.assertTrue(
                validationText.toLowerCase().contains(
                        "required"
                ),
                "Description validation missing"
        );

        Assert.assertTrue(
                validationText.toLowerCase().contains(
                        "required"
                ),
                "Duration validation missing"
        );

        // ===== STEP 3 : Invalid File Upload =====
        page.uploadInvalidFile(
                "C:\\Users\\Deepshika\\Desktop\\IdsNext Automation"
                        + "\\idsnext-automation\\Fxspa-Config"
                        + "\\src\\resources\\testdata\\sample.pdf"
        );

        Thread.sleep(2000);

        String toast2 =
                page.getToastMsgError();

        System.out.println(
                "Step3 Toast (Invalid File): "
                        + toast2
        );

        Assert.assertTrue(
                toast2.toLowerCase().contains("invalid")
                        || toast2.toLowerCase().contains("file"),
                "Invalid file upload toast not shown"
        );
    }
}