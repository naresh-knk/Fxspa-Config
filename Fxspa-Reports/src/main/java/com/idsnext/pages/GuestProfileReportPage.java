package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GuestProfileReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public GuestProfileReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isGuestProfileReportGenerated() {
        return isElementVisible(reportTable);
    }
}