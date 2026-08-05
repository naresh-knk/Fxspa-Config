package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.NegativePackagePage;
import com.idsnext.steps.NavigationSteps;

import utils.BaseTest;

public class NegativePackageTest extends BaseTest {

    @Test
    public void verifyPackageNegativeFlow() throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        NegativePackagePage page =
                new NegativePackagePage(driver);

        // ===== NAVIGATION =====

        navigationSteps.navigateToAddPage(
                ModuleName.PACKAGES
        );


        // ===== STEP 1: Click Save → Check Toast =====

        page.clickSave();

        String toast1 =
                page.getToastMessage();

        System.out.println(
                "Step1 Toast: " + toast1
        );

        Assert.assertTrue(
                toast1.toLowerCase().contains("add")
                        || toast1.toLowerCase().contains("required"),
                "Expected validation toast not shown"
        );

        // ===== STEP 2: Upload Invalid File =====

        page.uploadInvalidFile(
                "C:\\Users\\Deepshika\\Desktop\\IdsNext Automation\\idsnext-automation\\Fxspa-Config\\src\\resources\\testdata\\sample.pdf"
        );

        Thread.sleep(2000);

        String toast2 =
                page.getToastMsgError();

        System.out.println(
                "Step2 Toast (Invalid File): "
                        + toast2
        );

        Assert.assertTrue(
                toast2.toLowerCase().contains("invalid")
                        || toast2.toLowerCase().contains("format"),
                "Invalid file upload toast not shown"
        );
    }
}