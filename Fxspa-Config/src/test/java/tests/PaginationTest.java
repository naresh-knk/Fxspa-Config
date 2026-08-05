package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.PaginationPage;
import com.idsnext.steps.NavigationSteps;

import utils.BaseTest;

public class PaginationTest extends BaseTest {

    NavigationSteps navigationSteps;

    PaginationPage paginationPage;

    @BeforeMethod
    public void setup() {

        navigationSteps =
                new NavigationSteps(driver);

        paginationPage =
                new PaginationPage(driver);
    }

    // ===== COMMON METHOD =====

    private void verifyPaginationForModule(
            ModuleName moduleName
    ) {

        navigationSteps.navigateToModule(
                moduleName
        );

        // ===== ITEMS PER PAGE VALIDATION =====

        paginationPage.selectItemsPerPage("5");

        paginationPage.validateItemsPerPage(5);

        paginationPage.selectItemsPerPage("10");

        paginationPage.validateItemsPerPage(10);

        // ===== PAGINATION VALIDATION =====

        paginationPage.validatePagination();
    }

    // ===== TESTS =====

    @Test
    public void verifyServicePagination() {

        verifyPaginationForModule(
                ModuleName.SERVICES
        );
    }

    @Test
    public void verifyResourcesPagination() {

        verifyPaginationForModule(
                ModuleName.RESOURCES
        );
    }

    @Test
    public void verifyProductPagination() {

        verifyPaginationForModule(
                ModuleName.PRODUCTS
        );
    }

    @Test
    public void verifyStaffPagination() {

        verifyPaginationForModule(
                ModuleName.STAFF_MAPPING
        );
    }

    @Test
    public void verifyServiceCategoryPagination() {

        verifyPaginationForModule(
                ModuleName.SERVICE_CATEGORY
        );
    }

    @Test
    public void verifyProductCategoryPagination() {

        verifyPaginationForModule(
                ModuleName.PRODUCT_CATEGORY
        );
    }

    @Test
    public void verifyPackagePagination() {

        verifyPaginationForModule(
                ModuleName.PACKAGES
        );
    }

    @Test
    public void verifyAdvancePagination() {

        verifyPaginationForModule(
                ModuleName.ADVANCED_SETTINGS
        );
    }
}