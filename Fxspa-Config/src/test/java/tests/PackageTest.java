package tests;

import org.testng.annotations.Test;
import com.idsnext.enums.ModuleName;
import com.idsnext.pages.PackagePage;
import com.idsnext.steps.NavigationSteps;
import utils.AssertionUtils;
import utils.BaseTest;

public class PackageTest extends BaseTest {

    @Test
    public void verifyPackageNavigation() {
        // Initialize the steps and page objects
        NavigationSteps navigationSteps = new NavigationSteps(driver);
        PackagePage packagePage = new PackagePage(driver);

        navigationSteps.navigateToAddPage(ModuleName.PACKAGES);
        packagePage.createPackage();

        String actualToastMsg = packagePage.getToastMsg();
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

        NavigationSteps navigationSteps = new NavigationSteps(driver);
        PackagePage packagePage = new PackagePage(driver);

        navigationSteps.navigateToAddPage(ModuleName.PACKAGES);
        packagePage.resetPackage();

        // Assertions
        AssertionUtils.assertEqualsWithMessage(
                packagePage.getPackageNameValue(),
                "",
                "Package name reset successfully",
                "Package name not cleared"
        );

        AssertionUtils.assertEqualsWithMessage(
                packagePage.getPackageDescriptionValue(),
                "",
                "Description reset successfully",
                "Description not cleared"
        );
    }
}