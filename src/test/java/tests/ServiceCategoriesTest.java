package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.LoginPage;
import com.idsnext.pages.ServiceCategoriesPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class ServiceCategoriesTest extends BaseTest {

    @Test
    public void verifyServiceCategoriesNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        ServiceCategoriesPage serviceCategoriesPage = new ServiceCategoriesPage(driver);

        // Step 2: CLICK FX REPORTS     
        serviceCategoriesPage.clickFXSPAConfigIcon();
        serviceCategoriesPage.switchWindow();

        // Step 3: Open 3-dot menu
        serviceCategoriesPage.clickRandom();
        serviceCategoriesPage.clickServiceCategories();
        serviceCategoriesPage.clickRandom();
        serviceCategoriesPage.clickAdd();
        serviceCategoriesPage.createServiceCategories();

          // Step 4: Capture Toast Message
        String actualToastMsg = serviceCategoriesPage.getToastMsg();

        // Expected Toast Message
        String expectedToastMsg = "Service Category Successfully Created!";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Service Category created successfully",
                "Service Category not created"
        );

    }
}