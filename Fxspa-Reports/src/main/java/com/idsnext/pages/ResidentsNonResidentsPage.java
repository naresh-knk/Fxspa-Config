package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResidentsNonResidentsPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public ResidentsNonResidentsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isResidentsNonResidentsGenerated() {
        return isElementVisible(reportTable);
    }
}