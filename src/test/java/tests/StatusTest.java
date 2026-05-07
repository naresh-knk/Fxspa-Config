package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.SearchPage;
import com.idsnext.pages.StatusPage;
import utils.BaseTest;

public class StatusTest extends BaseTest {

    SearchPage searchPage;
    StatusPage statusPage;

    @BeforeMethod
    public void setup() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        searchPage = new SearchPage(driver);
        statusPage = new StatusPage(driver);

        searchPage.clickFXSPAConfigIcon();
        searchPage.switchWindow();
        searchPage.clickRandom();
    }

    // ===== COMMON METHOD =====
    private void verifyAllStatuses() {

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
        searchPage.selectModule(By.xpath("//span[normalize-space()='Services']"));
        verifyAllStatuses();
    }

    // ===== RESOURCES =====
    @Test
    public void verifyResourcesStatus() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Resources']"));
        verifyAllStatuses();
    }

    // ===== PRODUCTS =====
    @Test
    public void verifyProductStatus() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Products']"));
        verifyAllStatuses();
    }

    // ===== STAFF =====
    @Test
    public void verifyStaffStatus() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Staff Mapping']"));
        searchPage.clickRandom();
        verifyAllStatuses();
    }

    // ===== SERVICE CATEGORY =====
    @Test
    public void verifyServiceCategoryStatus() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Service Categories']"));
        verifyAllStatuses();
    }

    // ===== PRODUCT CATEGORY =====
    @Test
    public void verifyProductCategoryStatus() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Product Categories']"));
        verifyAllStatuses();
    }

    // ===== PACKAGE =====
    @Test
    public void verifyPackageStatus() {
        searchPage.selectModule(By.xpath("//span[text()=' Packages ']"));
        verifyAllStatuses();
    }

    // ===== ADVANCED SETTINGS =====
    @Test
    public void verifyAdvanceStatus() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Advanced Settings']"));
        verifyAllStatuses();
    }
}