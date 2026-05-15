package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.NegativePackagePage;
import com.idsnext.pages.PackagePage;

import utils.BaseTest;

public class NegativePackageTest extends BaseTest {

    @Test
    public void verifyPackageNegativeFlow() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        PackagePage PackagePage = new PackagePage(driver);
        NegativePackagePage page = new NegativePackagePage(driver);

        // ===== NAVIGATION =====
        PackagePage.clickFXSPAConfigIcon();
        PackagePage.switchWindow();
        PackagePage.clickRandom();
        PackagePage.clickPackage();
        PackagePage.clickRandom();
        PackagePage.clickAdd();

        // ===== STEP 1: Click Save → Check Toast =====
        page.clickSave();

        String toast1 = page.getToastMessage();
        System.out.println("Step1 Toast: " + toast1);

        Assert.assertTrue(
                toast1.toLowerCase().contains("add") || toast1.toLowerCase().contains("required"),
                "Expected validation toast not shown"
        );

        // ===== STEP 2: Click fields without data → Check validation =====
        page.clickSave();

        page.uploadInvalidFile("C:\\Users\\Deepshika\\Desktop\\IdsNext Automation\\idsnext-automation\\src\\resources\\testdata\\sample.pdf");
        Thread.sleep(2000);
        String toast2 = page.getToastMsgError();
        System.out.println("Step 2 Toast (Invalid File): " + toast2);

        Assert.assertTrue(
                toast2.toLowerCase().contains("invalid") || toast2.toLowerCase().contains("format"),
                "Invalid file upload toast not shown"
        );
    }
}