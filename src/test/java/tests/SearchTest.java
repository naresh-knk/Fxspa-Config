package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.SearchPage;
import utils.BaseTest;

public class SearchTest extends BaseTest {

    SearchPage searchPage;

    @BeforeMethod
    public void setup() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        searchPage = new SearchPage(driver);

        searchPage.clickFXSPAConfigIcon();
        searchPage.switchWindow();
        searchPage.clickRandom();
    }

    @Test
    public void verifyServiceSearch() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Services']"));
        searchPage.search("Service");
        searchPage.validateColumnValues("Service Name", "Service");
    }

    @Test
    public void verifyResourcesSearch() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Resources']"));
        searchPage.search("Resource");
        searchPage.validateColumnValues("Resource Name", "Resource");
    }
    

    @Test
    public void verifyProductSearch() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Products']"));
        searchPage.search("Product");
        searchPage.validateColumnValues("Product Name", "Product");
    }

    @Test
    public void verifyStaffSearch() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Staff Mapping']"));
        searchPage.clickRandom();
        searchPage.search("deep");
        searchPage.validateColumnValues("Staff", "deep");
    }

    @Test
    public void verifyServiceCategorySearch() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Service Categories']"));
        searchPage.search("Name");
        searchPage.validateColumnValues("Service Line", "Name");
    }

    @Test
    public void verifyProductCategorySearch() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Product Categories']"));
        searchPage.search("Product");
        searchPage.validateColumnValues("Product Line", "Product");
    }

    @Test
    public void verifyPackageSearch() {
        searchPage.selectModule(By.xpath("//span[text()=' Packages ']"));
        searchPage.search("0");
        searchPage.validateColumnValues("Code", "0");
    }

    @Test
    public void verifyAdvanceSearch() {
        searchPage.selectModule(By.xpath("//span[normalize-space()='Advanced Settings']"));
        //mat-header-cell[text()=' Operation ID ']
        searchPage.search("Advance");
        searchPage.validateColumnValues("Operation Name", "Advance");
    }
}
