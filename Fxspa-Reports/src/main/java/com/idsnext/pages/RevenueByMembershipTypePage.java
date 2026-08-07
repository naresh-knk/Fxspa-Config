package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RevenueByMembershipTypePage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public RevenueByMembershipTypePage(WebDriver driver) {
        super(driver);
    }

    public boolean isRevenueByMembershipTypeGenerated() {
        return isElementVisible(reportTable);
    }
}