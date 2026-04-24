package tests;

import org.testng.annotations.Test;

import com.idsnext.pages.ServicesPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class ServicesTest extends BaseTest {

    @Test
    public void verifyServicesNavigation() throws InterruptedException {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());

        ServicesPage servicesPage = new ServicesPage(driver);

        // Step 2: CLICK FX SPA    
        servicesPage.clickFXSPAConfigIcon();
        servicesPage.switchWindow();

        // Step 3: Open 3-dot menu
        servicesPage.clickRandom();
        servicesPage.clickServices();
        servicesPage.clickRandom();
        servicesPage.clickAdd();
        servicesPage.createServices();

String expectedServiceName = servicesPage.getCreatedServiceName();
String actualServiceName = servicesPage.getFirstRowServiceName();

AssertionUtils.assertEqualsWithMessage(
        actualServiceName,
        expectedServiceName,
        "Service created and visible in first row",
        "Service name mismatch in listing"
);
    }
}