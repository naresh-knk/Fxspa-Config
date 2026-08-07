package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpaCaptureReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public SpaCaptureReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSpaCaptureReportGenerated() {
        return isElementVisible(reportTable);
    }
}