package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppointmentReportPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    
    private By fxReportsIcon =
            By.xpath("//div[contains(@title,'FX Reports (FX Reports)')]");

    private By randomIcon =
            By.xpath("(//span[@class='icon-shuffle shuffle-ico'])[1]");
    private By randomIconNew=By.xpath("(//span[@class='fas fa-random ng-star-inserted'])");

    private By reportsLabel =
            By.xpath("//a[@id='Reports']");

    private By spaReports =
            By.xpath("//span[normalize-space()='SPA Reports']");

    private By appointmentReport =
            By.xpath("//span[normalize-space()='Appointment Report']");

          private By calendar = By.xpath("(//button[@aria-label=\"Open calendar\"])[1]");

        //    private By calendarDate = By.xpath("//button[@aria-label='1 April 2026']");
           
        private By generateButton = By.xpath("//button[contains(text(),'Generate Report')]");


    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By reportTable = By.xpath("//tr[1]");
private By leftArrow = By.xpath("//button[@aria-label='Previous month']");
private By totalGuest=By.xpath("//input[@placeholder='Total Guest in Hotel']");

    // ===== Constructor =====
    public AppointmentReportPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void clickFXReport() {
        waitForAngularIdle();
        WebElement fx =
                wait.until(ExpectedConditions.elementToBeClickable(fxReportsIcon));
        jsClick(fx);
        waitForAngularIdle();
    }


   public void clickRandom() {
    try {
        if (!driver.findElements(randomIcon).isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(randomIcon)).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(randomIconNew)).click();
        }
    } catch (Exception e) {
        wait.until(ExpectedConditions.elementToBeClickable(randomIconNew)).click();
    }
}

    public void addGuest(){
        type(totalGuest, "10");
    }

    public void clickReportSLabel() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(reportsLabel)).click();
    }

    public void clickSPAReport() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(spaReports)).click();
    }

    public void clickAppoitmentReport() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(appointmentReport)).click();
    }

    public void clickCalender() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(calendar)).click();

    }

    public void clickCalenderDate() {

    waitForAngularIdle();

    By targetDate =
            By.xpath("//button[@aria-label='1 June 2026']");

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
        wait.until(ExpectedConditions.elementToBeClickable(generateButton)).click();
        waitForAngularIdle();
    }

    public boolean isReportGenerated() {
        waitForAngularIdle();
        return isElementVisible(reportTable); // uses BasePage method
    }
}
