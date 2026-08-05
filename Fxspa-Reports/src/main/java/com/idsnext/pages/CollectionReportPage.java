package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CollectionReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public CollectionReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCollectionReportGenerated() {
        return isElementVisible(reportTable);
    }
}