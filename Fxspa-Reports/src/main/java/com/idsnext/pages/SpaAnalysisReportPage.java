package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpaAnalysisReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public SpaAnalysisReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSpaAnalysisReportGenerated() {
        return isElementVisible(reportTable);
    }
}