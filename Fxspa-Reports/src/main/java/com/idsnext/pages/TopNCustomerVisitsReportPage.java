package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopNCustomerVisitsReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public TopNCustomerVisitsReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTopNCustomerVisitsReportGenerated() {
        return isElementVisible(reportTable);
    }
}