package com.idsnext.steps;

import org.openqa.selenium.WebDriver;
import com.idsnext.pages.AppointmentReportPage;
import com.idsnext.pages.NavigationPage;
import com.idsnext.enums.ModuleName;

public class ReportNavigationSteps {

    private WebDriver driver;
    private NavigationPage navigationPage;
    private AppointmentReportPage reportPage;

    public ReportNavigationSteps(WebDriver driver) {
        this.driver = driver;
        this.navigationPage = new NavigationPage(driver);
        this.reportPage = new AppointmentReportPage(driver);
    }

    public void navigateToSpaReport(ModuleName module) {
        reportPage.clickRandom();
        reportPage.clickReportSLabel();
        reportPage.clickSPAReport();
        navigationPage.clickModule(module);
    }

    public void configureFiltersAndGenerate() {
        reportPage.clickCalender();
        reportPage.clickCalenderDate();
        reportPage.clickGenerate();
    }
}