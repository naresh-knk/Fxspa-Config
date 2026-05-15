package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.PackagePage;
import com.idsnext.pages.ServicesPage;
import com.idsnext.pages.EditPackagePage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditPackageTest extends BaseTest {

    @Test
    public void verifyEditPackageNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
       loginPage.login(config.getUsername(), config.getPassword());

        EditPackagePage editPackagePage = new EditPackagePage(driver);

        // Step 2: CLICK FX REPORTS     
        editPackagePage.clickFXSPAConfigIcon();
        editPackagePage.switchWindow();

        // Step 3: Open 3-dot menu
        editPackagePage.clickRandom();
        editPackagePage.clickPackage();
        editPackagePage.clickRandom();
        editPackagePage.clickRow();
        editPackagePage.updatePackage();

        
        // Step 4: Capture Toast Message
        String actualToastMsg = editPackagePage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Package Updated!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Package updated successfully",
                "Package not updated"
        );

    }

     @Test
    public void verifyResetFunctionality() {

    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(config.getUsername(), config.getPassword());

    EditPackagePage editPackagePage = new EditPackagePage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);

    editPackagePage.clickFXSPAConfigIcon();
    editPackagePage.switchWindow();

    servicesPage.clickRandom();
    editPackagePage.clickPackage();
    servicesPage.clickRandom();
    editPackagePage.clickAdd();

    // Click Reset
    editPackagePage.resetPackage();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            editPackagePage.getPackageNameValue(),
            "",
            "Package name reset successfully",
            "Package name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            editPackagePage.getPackageDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );
}
}