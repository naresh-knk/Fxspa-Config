package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserManagementListPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public UserManagementListPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserManagementListGenerated() {
        return isElementVisible(reportTable);
    }
}