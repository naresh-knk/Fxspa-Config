package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MasterExportReportPage extends BasePage{

    private WebDriver driver;
    private WebDriverWait wait;


    private By randomIcon =
            By.xpath("(//span[contains(@class,'icon-shuffle shuffle-ico')])[1]");

    private By reportsLabel =
            By.xpath("//a[@id='Reports']");

    private By spaReports =
            By.xpath("//span[normalize-space()='SPA Reports']");

    private By bRefusal =
            By.xpath("(//span[normalize-space()='Bill Print Audit Report'])[2]");

        
        private By generateButton = By.xpath("//button[contains(text(),'Generate Report')]");
        private By reportTable = By.xpath("//th[1]");



    // ===== Constructor =====
    public MasterExportReportPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }


    // ===== Actions =====


    public void clickRandom() {
        wait.until(ExpectedConditions.elementToBeClickable(randomIcon)).click();
    }

    public void clickReportSLabel() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(reportsLabel)).click();
    }

    public void clickSPAReport() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(spaReports)).click();
    }

    public void clickbRefusal() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(bRefusal)).click();
    }


public void clickGenerate() {

    waitForAngularIdle();

    // wait for overlay/backdrop to disappear
    wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.cssSelector(".cdk-overlay-backdrop-showing")));

    WebElement generateElement = wait.until(
            ExpectedConditions.elementToBeClickable(generateButton));

    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", generateElement);

    //waitForAngularIdle();
}


    public boolean isMasterExportReportGenerated() {
        return isElementVisible(reportTable); // uses BasePage method
    }
}
