package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateAdvancedSettingsPage extends BasePage {

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

    private By resources=
            By.xpath("//span[normalize-space()='Advanced Settings']");

            private By plusButton = By.xpath("//button[normalize-space()='+']");

    private By operationName= By.xpath("//mat-label[normalize-space()='Operation Name']/ancestor::mat-form-field//input");
    private By operationID= By.xpath("//mat-label[normalize-space()='Operation ID']/ancestor::mat-form-field//input");
    private By desc= By.xpath("//mat-label[normalize-space()='Description']/ancestor::mat-form-field//input");
    private String AdvancedSettings;
    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");


    // ===== Constructor =====
    public CreateAdvancedSettingsPage(WebDriver driver) {
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
       // Thread.sleep(10000); // Temporary wait to observe the click action, replace with proper wait if needed
    }


    public void clickAdvancedSettings() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(resources)).click();
        waitForAngularIdle();
    }

     public void clickAdd() {
        waitForAngularIdle();

        WebElement plus =
                wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        jsClick(plus);
    }

    public void createAdvancedSettings() {

        String randomName = RandomStringUtils.randomAlphabetic(4);
        String randomNumeric = RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        

        wait.until(ExpectedConditions.visibilityOfElementLocated(operationName))
                .sendKeys("Name " + randomName);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(operationID))
                .sendKeys(randomNumeric);
        wait.until(ExpectedConditions.visibilityOfElementLocated(desc))
                .sendKeys("Desc " + randomName);
        
        waitForAngularIdle();

        WebElement create =
                wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
        jsClick(create);
        //waitForAngularIdle();

    }

    public String getAdvancedSettings() {
    return AdvancedSettings.trim();
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