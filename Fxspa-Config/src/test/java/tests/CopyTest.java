package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.CopyPage;
import com.idsnext.steps.NavigationSteps;

import utils.BaseTest;

public class CopyTest extends BaseTest {

    NavigationSteps navigationSteps;

    CopyPage copyPage;

    @BeforeMethod
    public void setup() {

        navigationSteps =
                new NavigationSteps(driver);

        copyPage =
                new CopyPage(driver);
    }

    // ===== COMMON METHOD =====

    private void verifyCopyForModule(
            ModuleName moduleName
    ) {

        navigationSteps.navigateToModule(
                moduleName
        );

        copyPage.performCopy();
    }

    // ===== TESTS =====

    @Test
    public void verifyServiceCopy() {

        verifyCopyForModule(
                ModuleName.SERVICES
        );
    }

    @Test
    public void verifyResourcesCopy() {

        verifyCopyForModule(
                ModuleName.RESOURCES
        );
    }

    @Test
    public void verifyProductCopy() {

        verifyCopyForModule(
                ModuleName.PRODUCTS
        );
    }

    @Test
    public void verifyStaffCopy() {

        verifyCopyForModule(
                ModuleName.STAFF_MAPPING
        );
    }

    @Test
    public void verifyServiceCategoryCopy() {

        verifyCopyForModule(
                ModuleName.SERVICE_CATEGORY
        );
    }

    @Test
    public void verifyProductCategoryCopy() {

        verifyCopyForModule(
                ModuleName.PRODUCT_CATEGORY
        );
    }

    @Test
    public void verifyPackageCopy() {

        verifyCopyForModule(
                ModuleName.PACKAGES
        );
    }
}