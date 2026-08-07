package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopNServiceProductSalesReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public TopNServiceProductSalesReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTopNServiceProductSalesReportGenerated() {
        return isElementVisible(reportTable);
    }
}