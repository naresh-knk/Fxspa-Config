package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CollectionSummaryReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public CollectionSummaryReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCollectionSummaryReportGenerated() {
        return isElementVisible(reportTable);
    }

}