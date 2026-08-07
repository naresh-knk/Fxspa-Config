package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaffUtilizationPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public StaffUtilizationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isStaffUtilizationGenerated() {
        return isElementVisible(reportTable);
    }
}