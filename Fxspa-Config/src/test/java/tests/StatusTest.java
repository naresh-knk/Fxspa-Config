package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.steps.NavigationSteps;
import com.idsnext.pages.StatusPage;

import utils.BaseTest;

public class StatusTest extends BaseTest {

    NavigationSteps navigationSteps;

    StatusPage statusPage;

    @BeforeMethod
    public void setup() {

        navigationSteps =
                new NavigationSteps(driver);

        statusPage =
                new StatusPage(driver);
    }

    // ===== COMMON METHOD =====

    private void verifyAllStatuses(
            ModuleName moduleName
    ) {

        navigationSteps.navigateToModule(
                moduleName
        );

        statusPage.selectStatus("Active");

        statusPage.validateStatus("active");

        statusPage.selectStatus("Inactive");

        statusPage.validateStatus("inactive");

        statusPage.selectStatus("All");

        statusPage.validateStatus("all");
    }

    // ===== SERVICE =====

    @Test
    public void verifyServiceStatus() {

        verifyAllStatuses(
                ModuleName.SERVICES
        );
    }

    // ===== RESOURCES =====

    @Test
    public void verifyResourcesStatus() {

        verifyAllStatuses(
                ModuleName.RESOURCES
        );
    }

    // ===== PRODUCTS =====

    @Test
    public void verifyProductStatus() {

        verifyAllStatuses(
                ModuleName.PRODUCTS
        );
    }

    // ===== STAFF =====

    @Test
    public void verifyStaffStatus() {

        verifyAllStatuses(
                ModuleName.STAFF_MAPPING
        );
    }

    // ===== SERVICE CATEGORY =====

    @Test
    public void verifyServiceCategoryStatus() {

        verifyAllStatuses(
                ModuleName.SERVICE_CATEGORY
        );
    }

    // ===== PRODUCT CATEGORY =====

    @Test
    public void verifyProductCategoryStatus() {

        verifyAllStatuses(
                ModuleName.PRODUCT_CATEGORY
        );
    }

    // ===== PACKAGE =====

    @Test
    public void verifyPackageStatus() {

        verifyAllStatuses(
                ModuleName.PACKAGES
        );
    }

    // ===== ADVANCED SETTINGS =====

    @Test
    public void verifyAdvanceStatus() {

        verifyAllStatuses(
                ModuleName.ADVANCED_SETTINGS
        );
    }
}