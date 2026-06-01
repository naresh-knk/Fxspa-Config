package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.SearchPage;
import com.idsnext.steps.NavigationSteps;

import utils.BaseTest;

public class SearchTest extends BaseTest {

    NavigationSteps navigationSteps;

    SearchPage searchPage;

    @BeforeMethod
    public void setup() {

        navigationSteps =
                new NavigationSteps(driver);

        searchPage =
                new SearchPage(driver);
    }

    // ===== COMMON METHOD =====

    private void verifySearch(
            ModuleName moduleName,
            String searchValue,
            String columnName
    ) {

        navigationSteps.navigateToModule(
                moduleName
        );

        searchPage.search(searchValue);

        searchPage.validateColumnValues(
                columnName,
                searchValue
        );
    }

    // ===== SERVICES =====

    @Test
    public void verifyServiceSearch() {

        verifySearch(
                ModuleName.SERVICES,
                "Service",
                "Service Name"
        );
    }

    // ===== RESOURCES =====

    @Test
    public void verifyResourcesSearch() {

        verifySearch(
                ModuleName.RESOURCES,
                "Resource",
                "Resource Name"
        );
    }

    // ===== PRODUCTS =====

    @Test
    public void verifyProductSearch() {

        verifySearch(
                ModuleName.PRODUCTS,
                "Product",
                "Product Name"
        );
    }

    // ===== STAFF =====

    @Test
    public void verifyStaffSearch() {

        verifySearch(
                ModuleName.STAFF_MAPPING,
                "deep",
                "Staff"
        );
    }

    // ===== SERVICE CATEGORY =====

    @Test
    public void verifyServiceCategorySearch() {

        verifySearch(
                ModuleName.SERVICE_CATEGORY,
                "Name",
                "Service Line"
        );
    }

    // ===== PRODUCT CATEGORY =====

    @Test
    public void verifyProductCategorySearch() {

        verifySearch(
                ModuleName.PRODUCT_CATEGORY,
                "Product",
                "Product Line"
        );
    }

    // ===== PACKAGE =====

    @Test
    public void verifyPackageSearch() {

        verifySearch(
                ModuleName.PACKAGES,
                "0",
                "Code"
        );
    }

    // ===== ADVANCED SETTINGS =====

    @Test
    public void verifyAdvanceSearch() {

        verifySearch(
                ModuleName.ADVANCED_SETTINGS,
                "Enable",
                "Operation Name"
        );
    }
}