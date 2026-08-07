package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurityShellReportPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public SecurityShellReportPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSecurityShellReportGenerated() {
        return isElementVisible(reportTable);
    }
}