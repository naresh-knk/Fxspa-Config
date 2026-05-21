package tests;

import org.testng.annotations.Test;
import com.idsnext.pages.ServicesPage;
import utils.AssertionUtils;
import utils.BaseTest;

public class ServicesTest extends BaseTest {

    @Test
    public void verifyServicesNavigation() throws InterruptedException {

        ServicesPage servicesPage = new ServicesPage(driver);
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

   @Test
public void verifyResetFunctionality() {

    ServicesPage servicesPage = new ServicesPage(driver);
    servicesPage.clickRandom();
    servicesPage.clickServices();
    servicesPage.clickRandom();
    servicesPage.clickAdd();

    // Click Reset
    servicesPage.resetServices();

    // Assertions

    AssertionUtils.assertEqualsWithMessage(
            servicesPage.getServiceNameValue(),
            "",
            "Service name reset successfully",
            "Service name not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            servicesPage.getServiceDescriptionValue(),
            "",
            "Description reset successfully",
            "Description not cleared"
    );

    AssertionUtils.assertEqualsWithMessage(
            servicesPage.getServiceDurationValue(),
            "",
            "Duration reset successfully",
            "Duration not cleared"
    );
}


}