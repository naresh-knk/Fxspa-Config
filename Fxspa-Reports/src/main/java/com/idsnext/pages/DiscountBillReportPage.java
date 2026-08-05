package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DiscountBillReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public DiscountBillReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDiscountBillReportGenerated() {
        return isElementVisible(reportTable);
    }
}