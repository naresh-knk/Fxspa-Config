package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerRevenueAnalysisReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public CustomerRevenueAnalysisReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCustomerAnalysisReportGenerated() {
        return isElementVisible(reportTable);
    }
}