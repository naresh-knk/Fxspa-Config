package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CashierReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public CashierReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCashierReportGenerated() {
        return isElementVisible(reportTable);
    }
}