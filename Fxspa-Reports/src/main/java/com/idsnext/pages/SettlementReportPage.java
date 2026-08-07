package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettlementReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public SettlementReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSettlementReportGenerated() {
        return isElementVisible(reportTable);
    }
}