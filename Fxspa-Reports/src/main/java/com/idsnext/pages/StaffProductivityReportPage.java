package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaffProductivityReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public StaffProductivityReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isStaffProductivityReportGenerated() {
        return isElementVisible(reportTable);
    }
}