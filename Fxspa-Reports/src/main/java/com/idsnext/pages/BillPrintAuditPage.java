package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BillPrintAuditPage extends BasePage{

    private WebDriver driver;
    private WebDriverWait wait;

    
    private By fxReportsIcon =
            By.xpath("//div[contains(@title,'FX Reports (FX Reports)')]");

    private By randomIcon =
            By.xpath("(//span[contains(@class,'icon-shuffle shuffle-ico')])[1]");

    private By reportsLabel =
            By.xpath("//a[@id='Reports']");

    private By spaReports =
            By.xpath("//span[normalize-space()='SPA Reports']");

    private By bRefusal =
            By.xpath("(//span[normalize-space()='Bill Print Audit Report'])[2]");

          private By calendar = By.xpath("(//mat-datepicker-toggle//button[@aria-label='Open calendar'])[1]");

        //    private By calendarDate = By.xpath("//button[@aria-label=\"2 December 2025\"]");
           private By leftArrow = By.xpath("//*[@id=\"mat-datepicker-0\"]/mat-calendar-header/div/div/button[2]");

        private By generateButton = By.xpath("//button[contains(text(),'Generate Report')]");
        private By reportTable = By.xpath("//th[1]");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By outlets= By.xpath("//mat-label[text()='Outlets']");

    // ===== Constructor =====
    public BillPrintAuditPage(WebDriver driver) {
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

//     public void clickOutlets() {
//         waitForAngularIdle();
//         selectFromMatDropdownByIndex(outlets, 0);
//     }

public void clickCalender() {

    waitForAngularIdle();

    // wait for overlay/backdrop to disappear
    wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.cssSelector(".cdk-overlay-backdrop-showing")));

    WebElement calendarElement = wait.until(
            ExpectedConditions.elementToBeClickable(calendar));

    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", calendarElement);
}


   public void clickCalenderDate() {

    waitForAngularIdle();

    By targetDate =
            By.xpath("//button[@aria-label='1 April 2026']");

    int maxAttempts = 24;

    for (int i = 0; i < maxAttempts; i++) {

        // Check if date exists
        if (driver.findElements(targetDate).size() > 0) {

            WebElement date =
                    wait.until(ExpectedConditions.elementToBeClickable(targetDate));

            jsClick(date);

            System.out.println("Date Found And Selected");

            return;
        }

        // Click previous month arrow
        WebElement previousArrow =
                wait.until(ExpectedConditions.elementToBeClickable(leftArrow));

        jsClick(previousArrow);

        waitForAngularIdle();
    }

    throw new NoSuchElementException(
            "Could not find target date after clicking previous arrow multiple times"
    );
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


    public boolean isBillPrintAuditReportGenerated() {
        return isElementVisible(reportTable); // uses BasePage method
    }
}
