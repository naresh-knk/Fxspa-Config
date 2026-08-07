package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemSaleAnalysisPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public ItemSaleAnalysisPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItemSaleAnalysisReportGenerated() {
        return isElementVisible(reportTable);
    }
}