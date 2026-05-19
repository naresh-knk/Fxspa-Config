package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.idsnext.pages.AppointmentCalendarPage;
import com.idsnext.pages.LoginPage;

import utils.AssertionUtils;
import utils.BaseTest;

public class AppointmentCalendarTest extends BaseTest {

    AppointmentCalendarPage AppointmentCalendarPage;

    @BeforeMethod
    public void setup() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(config.getUsername(), config.getPassword());

       AppointmentCalendarPage = new AppointmentCalendarPage(driver);

        AppointmentCalendarPage.clickFXOperations();

        AppointmentCalendarPage.switchToLatestTab();

        AppointmentCalendarPage.clickRandom();

        AppointmentCalendarPage.clickAppointmentCalendar();
        AppointmentCalendarPage.clickRandom();
        AppointmentCalendarPage.clickNewCalendar();
        AppointmentCalendarPage.clickOutlets();
        AppointmentCalendarPage.nextButton();
        AppointmentCalendarPage.clickRandomAvailableSlot();
        AppointmentCalendarPage.createNewAppointment();
}

    @Test
    public void verifyAllAppointmentCreated() {

        AppointmentCalendarPage.saveAppointment();

        String actualToastMsg = AppointmentCalendarPage.getToastMsg();
        String expectedToastMsg = "Appointment booked successfully";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Appointment created successfully",
                "Appointment not created"
        );
    
    }

    @Test
    public void verifyAddGuest() {

        AppointmentCalendarPage.addGuest();
        AppointmentCalendarPage.saveAppointment();

        String actualToastMsg = AppointmentCalendarPage.getToastMsg();
        String expectedToastMsg = "Appointment booked successfully";

        // Assertion
        AssertionUtils.assertEqualsWithMessage(
                actualToastMsg,
                expectedToastMsg,
                "Appointment created successfully",
                "Appointment not created"
        );
    
    }
}