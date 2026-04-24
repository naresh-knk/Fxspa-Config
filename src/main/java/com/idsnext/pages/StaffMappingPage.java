package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StaffMappingPage extends BasePage {

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
            By.xpath("//span[normalize-space()='Staff Mapping']");

            private By plusButton = By.xpath("//button[normalize-space()='+']");

    private By selectStaffName= By.xpath("//span[text()='Staff Name']");
    private By ok= By.xpath("//button[text()=' OK ']");
    private By checkBox= By.xpath("(//div[@class='checkbox']//label[contains(@class,'mat-checkbox-layout')])[1]");
    private String StaffMapping;
    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");


    // ===== Constructor =====
    public StaffMappingPage(WebDriver driver) {
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


    public void clickStaffMapping() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(resources)).click();
        waitForAngularIdle();
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

     public void clickAdd() {
        waitForAngularIdle();

        WebElement plus =
                wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        jsClick(plus);
    }

    public void createStaffMapping() {

        waitForAngularIdle();

        selectFromMatDropdownByIndex(selectStaffName, 0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ok))
                .click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox))
                .click();
        
        waitForAngularIdle();

        WebElement create =
                wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
        jsClick(create);
        //waitForAngularIdle();

    }

    public String getStaffMapping() {
    return StaffMapping.trim();
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