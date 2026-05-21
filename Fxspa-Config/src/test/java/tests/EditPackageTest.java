package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.ServicesPage;
import com.idsnext.pages.EditPackagePage;
import utils.AssertionUtils;
import utils.BaseTest;

public class EditPackageTest extends BaseTest {

    @Test
    public void verifyEditPackageNavigation() throws InterruptedException {

        EditPackagePage editPackagePage = new EditPackagePage(driver);

        editPackagePage.clickRandom();
        editPackagePage.clickPackage();
        editPackagePage.clickRandom();
        editPackagePage.clickRow();
        editPackagePage.updatePackage();

        
        // Capture Toast Message
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

    EditPackagePage editPackagePage = new EditPackagePage(driver);
    ServicesPage servicesPage=new ServicesPage(driver);

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