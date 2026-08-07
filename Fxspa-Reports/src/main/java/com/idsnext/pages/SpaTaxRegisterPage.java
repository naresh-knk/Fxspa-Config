package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpaTaxRegisterPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public SpaTaxRegisterPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSpaTaxRegisterGenerated() {
        return isElementVisible(reportTable);
    }
}