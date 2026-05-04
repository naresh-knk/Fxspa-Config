package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BillSeriesSetupPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    
    private By fxSPAConfigIcon =
            By.xpath("//div[contains(@title,'FX SPA Configuration (FX SPA)')]");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By randomIcon =
           By.xpath("/html/body/app-root/div[1]/nav/ul/li/a/span[1]");

    private By billSeries=
            By.xpath("//span[text()=' Bill Series Setup ']");

            private By plusButton = By.xpath("//button[normalize-space()='+']");

    private By outlet= By.xpath("(//mat-select[@name='outlet'])[2]");
    private By kotNumber= By.xpath("//input[@formcontrolname='kotNumber']");
    private By billNumber= By.xpath("//input[@formcontrolname='billNumber']");
    private String createdBillSeries;  
    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");


    // ===== Constructor =====
    public BillSeriesSetupPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== Common Waits =====

    private void waitForAngularIdle() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception ignored) {}

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayBackdrop));
        } catch (Exception ignored) {}
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    private void selectFromMatDropdownByIndex(By dropdown, int index) {
    waitForAngularIdle();

    WebElement dd =
            wait.until(ExpectedConditions.elementToBeClickable(dropdown));
    jsClick(dd);

    By option =
            By.xpath("(//mat-option//span)[" + (index + 1) + "]");
    WebElement opt =
            wait.until(ExpectedConditions.elementToBeClickable(option));
    jsClick(opt);

    waitForAngularIdle();
}

    // ===== Actions =====


    public void clickFXSPAConfigIcon() {
        waitForAngularIdle();
        WebElement fx =
                wait.until(ExpectedConditions.elementToBeClickable(fxSPAConfigIcon));
        jsClick(fx);
        waitForAngularIdle();
    }

    public void switchWindow(){
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

       public void clickRandom() throws InterruptedException {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(randomIcon)).click();
       }


    public void clickBillSeries() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(billSeries)).click();
        waitForAngularIdle();
    }

     public void clickAdd() {
        waitForAngularIdle();

        WebElement plus =
                wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        jsClick(plus);
    }

 public void createBillSeries() {

    // String randomName = RandomStringUtils.randomAlphabetic(4);
    String randomNumeric = RandomStringUtils.randomNumeric(2);

    // createdBillSeries = "Name " + randomName;

    waitForAngularIdle();

    selectFromMatDropdownByIndex(outlet, 0);

    wait.until(ExpectedConditions.visibilityOfElementLocated(kotNumber))
            .sendKeys(randomNumeric);

    wait.until(ExpectedConditions.visibilityOfElementLocated(billNumber))
            .sendKeys(randomNumeric);

    // ===== START DATE HANDLING =====
    WebElement dateField = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//input[@formcontrolname='startDate']"))
    );

    // Clear existing date
    dateField.click();
    dateField.sendKeys(Keys.CONTROL + "a");
    dateField.sendKeys(Keys.BACK_SPACE);

    // Generate dynamic future date
    LocalDate date = LocalDate.now().plusDays(1); // change if needed

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = date.format(formatter);

    // Enter new date
    dateField.sendKeys(formattedDate);
    dateField.sendKeys(Keys.TAB); // close picker

    // ===== END DATE HANDLING =====

    waitForAngularIdle();

    WebElement create =
            wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));

    jsClick(create);
}

 public String getCreatedBill() {
    return createdBillSeries.trim();
}

public String getToastMsg() {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    for (int i = 0; i < 15; i++) {

        String toast = (String) js.executeScript(
                "var e=document.querySelector('#toast-popup p'); return e?e.innerText:'';"
        );

        if (!toast.isEmpty()) {
            return toast.trim();
        }

        try {
            Thread.sleep(200);
        } catch (Exception e) {}
    }

    return "";
}
}

