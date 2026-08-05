
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.NegativeResourcesPage;
import com.idsnext.steps.NavigationSteps;

import utils.BaseTest;

public class NegativeResourcesTest extends BaseTest {

    @Test
    public void verifyResourcesNegativeFlow()
            throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        NegativeResourcesPage page =
                new NegativeResourcesPage(driver);

        // ===== NAVIGATION =====

        navigationSteps.navigateToAddPage(
                ModuleName.RESOURCES
        );

        // ===== STEP 1: Click Save → Check Toast =====

        page.clickSave();

        String toast1 =
                page.getToastMessage();

        System.out.println(
                "Step1 Toast: "
                        + toast1
        );

        Assert.assertTrue(
                toast1.toLowerCase().contains("incorrect")
                        || toast1.toLowerCase().contains("required"),

                "Expected validation toast not shown"
        );

        // ===== STEP 2: Empty Field Validation =====

        page.clickFirstTwoFieldsWithoutData();

        page.clickSave();

        boolean validationVisible =
                page.isValidationDisplayed();

        String validationText =
                page.getFirstTwoFieldErrors();

        System.out.println(
                "Step2 Validation: "
                        + validationText
        );

        Assert.assertTrue(
                validationVisible,
                "Validation messages not displayed"
        );

        Assert.assertTrue(
                validationText.toLowerCase().contains("required"),
                "Resources Name validation missing"
        );

        Assert.assertTrue(
                validationText.toLowerCase().contains("required"),
                "Description validation missing"
        );

        // ===== STEP 3: Upload Invalid File =====

        page.uploadInvalidFile(
                "C:\\Users\\Deepshika\\Desktop\\IdsNext Automation\\idsnext-automation\\Fxspa-Config\\src\\resources\\testdata\\sample.pdf"
        );

        Thread.sleep(3000);

        String toast2 =
                page.getToastMsgError();

        System.out.println(
                "Step3 Toast (Invalid File): "
                        + toast2
        );

        Assert.assertTrue(

                toast2.toLowerCase().contains("invalid")
                        || toast2.toLowerCase().contains("format")
                        || toast2.toLowerCase().contains("file")
                        || toast2.toLowerCase().contains("image")
                        || toast2.toLowerCase().contains("upload"),

                "Invalid file upload toast not shown"
                        + " Actual Toast: "
                        + toast2
        );
    }
}
