package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListBillReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public ListBillReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isListBillReportGenerated() {
        return isElementVisible(reportTable);
    }
}