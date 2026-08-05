package tests;

import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.EditPackagePage;
import com.idsnext.steps.NavigationSteps;

import utils.AssertionUtils;
import utils.BaseTest;

public class EditPackageTest extends BaseTest {

    //@Test
    public void verifyEditPackageNavigation() throws InterruptedException {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        EditPackagePage editPackagePage =
                new EditPackagePage(driver);

        // Navigate to Packages module
        navigationSteps.navigateToModule(ModuleName.PACKAGES);

        // Open existing package and update
        editPackagePage.clickRow();
        editPackagePage.updatePackage();

        // Toast message validation
        String actualToastMsg =
                editPackagePage.getToastMsg();

        String expectedToastMsg =
                "Package Updated!";

        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Package updated successfully",
                "Package not updated"
        );
    }

    //Reset functionality of edit package is not working
   // @Test     
    public void verifyResetFunctionality() {

        NavigationSteps navigationSteps =
                new NavigationSteps(driver);

        EditPackagePage editPackagePage =
                new EditPackagePage(driver);

        // Navigate to Packages module
        navigationSteps.navigateToModule(ModuleName.PACKAGES);

        // Open existing package
        editPackagePage.clickRow();

        // Reset package
        editPackagePage.resetPackage();

        AssertionUtils.assertEqualsWithMessage(
                editPackagePage.getPackageDescriptionValue(),
                "",
                "Description reset successfully",
                "Description not cleared"
        );
    }
}