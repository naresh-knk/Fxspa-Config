package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComplimentaryReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public ComplimentaryReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isComplimentaryReportGenerated() {
        return isElementVisible(reportTable);
    }
}