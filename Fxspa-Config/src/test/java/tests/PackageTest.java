package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.PackagePage;
import com.idsnext.pages.ServicesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class PackageTest extends BaseTest {

    @Test
    public void verifyPackageNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
       loginPage.login(config.getUsername(), config.getPassword());

        PackagePage packagePage = new PackagePage(driver);

        // Step 2: CLICK FX REPORTS     
        packagePage.clickFXSPAConfigIcon();
        packagePage.switchWindow();

        // Step 3: Open 3-dot menu
        packagePage.clickRandom();
        packagePage.clickPackage();
        packagePage.clickRandom();
        packagePage.clickAdd();
        packagePage.createPackage();

        
        // Step 4: Capture Toast Message
        String actualToastMsg = packagePage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Package Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Package created successfully",
                "Package not created"
        );

    }

     @Test
    public void verifyResetFunctionality() {

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(config.getUsername(), config.getPassword());

    PackagePage PackagePage = new PackagePage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);

    PackagePage.clickFXSPAConfigIcon();
    PackagePage.switchWindow();

    servicesPage.clickRandom();
    PackagePage.clickPackage();
    servicesPage.clickRandom();
    PackagePage.clickAdd();

    // Click Reset
    PackagePage.resetPackage();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            PackagePage.getPackageNameValue(),
            "",
            "Package name reset successfully",
            "Package name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            PackagePage.getPackageDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );
}
}