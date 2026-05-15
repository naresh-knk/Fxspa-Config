package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.PaginationPage;
import com.idsnext.pages.SearchPage;
import utils.BaseTest;

public class PaginationTest extends BaseTest {

    SearchPage searchPage;
    PaginationPage paginationPage;

    @BeforeMethod
    public void setup() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        searchPage = new SearchPage(driver);
        paginationPage = new PaginationPage(driver);

        searchPage.clickFXSPAConfigIcon();
        searchPage.switchWindow();
        searchPage.clickRandom();
    }

    // ===== COMMON METHOD =====
    private void verifyPaginationForModule(By moduleLocator, boolean isStaff) {

        searchPage.selectModule(moduleLocator);

        if (isStaff) {
            searchPage.clickRandom();
        }

        // ITEMS PER PAGE VALIDATION
        paginationPage.selectItemsPerPage("5");
        paginationPage.validateItemsPerPage(5);

        paginationPage.selectItemsPerPage("10");
        paginationPage.validateItemsPerPage(10);

        // PAGINATION VALIDATION
        paginationPage.validatePagination();
    }

    // ===== TESTS =====

    @Test
    public void verifyServicePagination() {
        verifyPaginationForModule(
                By.xpath("//span[normalize-space()='Services']"), false);
    }

    @Test
    public void verifyResourcesPagination() {
        verifyPaginationForModule(
                By.xpath("//span[normalize-space()='Resources']"), false);
    }

    @Test
    public void verifyProductPagination() {
        verifyPaginationForModule(
                By.xpath("//span[normalize-space()='Products']"), false);
    }

    @Test
    public void verifyStaffPagination() {
        verifyPaginationForModule(
                By.xpath("//span[normalize-space()='Staff Mapping']"), true);
    }

    @Test
    public void verifyServiceCategoryPagination() {
        verifyPaginationForModule(
                By.xpath("//span[normalize-space()='Service Categories']"), false);
    }

    @Test
    public void verifyProductCategoryPagination() {
        verifyPaginationForModule(
                By.xpath("//span[normalize-space()='Product Categories']"), false);
    }

    @Test
    public void verifyPackagePagination() {
        verifyPaginationForModule(
                By.xpath("//span[text()=' Packages ']"), false);
    }

    @Test
    public void verifyAdvancePagination() {
        verifyPaginationForModule(
                By.xpath("//span[normalize-space()='Advanced Settings']"), false);
    }
}