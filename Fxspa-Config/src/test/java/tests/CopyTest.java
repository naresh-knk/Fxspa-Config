package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.pages.CopyPage;
import com.idsnext.pages.SearchPage;
import utils.BaseTest;

public class CopyTest extends BaseTest {

    SearchPage searchPage;
    CopyPage copyPage;

    @BeforeMethod
    public void setup() {

        searchPage = new SearchPage(driver);
        copyPage = new CopyPage(driver);
        searchPage.clickRandom();
    }

    // ===== COMMON METHOD =====

    private void verifyCopyForModule(By moduleLocator, boolean isStaff) {

        searchPage.selectModule(moduleLocator);

        if (isStaff) {
            searchPage.clickRandom();
        }

        copyPage.performCopy();
    }

    // ===== TESTS =====

    @Test
    public void verifyServiceCopy() {
        verifyCopyForModule(
                By.xpath("//span[normalize-space()='Services']"), false);
    }

    @Test
    public void verifyResourcesCopy() {
        verifyCopyForModule(
                By.xpath("//span[normalize-space()='Resources']"), false);
    }

    @Test
    public void verifyProductCopy() {
        verifyCopyForModule(
                By.xpath("//span[normalize-space()='Products']"), false);
    }

    @Test
    public void verifyStaffCopy() {
        verifyCopyForModule(
                By.xpath("//span[normalize-space()='Staff Mapping']"), true);
    }

    @Test
    public void verifyServiceCategoryCopy() {
        verifyCopyForModule(
                By.xpath("//span[normalize-space()='Service Categories']"), false);
    }

    @Test
    public void verifyProductCategoryCopy() {
        verifyCopyForModule(
                By.xpath("//span[normalize-space()='Product Categories']"), false);
    }

    @Test
    public void verifyPackageCopy() {
        verifyCopyForModule(
                By.xpath("//span[text()=' Packages ']"), false);
    }
}