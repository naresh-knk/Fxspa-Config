package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.PackagePage;
import com.idsnext.pages.ServicesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class PackageTest extends BaseTest {

    @Test
    public void verifyPackageNavigation() throws InterruptedException {

        PackagePage packagePage = new PackagePage(driver);

        packagePage.clickRandom();
        packagePage.clickPackage();
        packagePage.clickRandom();
        packagePage.clickAdd();
        packagePage.createPackage();

        
        // Capture Toast Message
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

    PackagePage PackagePage = new PackagePage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);

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