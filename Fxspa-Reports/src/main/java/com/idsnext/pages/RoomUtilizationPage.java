package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RoomUtilizationPage extends BasePage {

    private By reportTable = By.xpath("//th[1]");

    public RoomUtilizationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isRoomUtilizationGenerated() {
        return isElementVisible(reportTable);
    }
}