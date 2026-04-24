package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResourcesPage extends BasePage {

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
            By.xpath("//span[normalize-space()='Resources']");

            private By plusButton = By.xpath("//button[normalize-space()='+']");

    private By rname= By.xpath("//input[@placeholder='Resource Name']");
    private By rdesc= By.xpath("//input[contains(@placeholder,'Description')]");
    private By selectEquipment = By.xpath("//span[text()='Equipment']");
    private By ranclick= By.xpath("(//mat-option[@role='option'])[1]");
    private By property=By.xpath("//span[text()='Property']");
    private By spaOutlets= By.xpath("//span[text()='Spa Outlets']");
    private By quantity = By.xpath("//input[@placeholder='Quantity']");
    private String CreatedResources;
  

    
    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");


    // ===== Constructor =====
    public ResourcesPage(WebDriver driver) {
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


    public void clickResources() {
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

    public void createResources() {

        String randomName = RandomStringUtils.randomAlphabetic(4);
        String randomNumeric = RandomStringUtils.randomNumeric(1);

        waitForAngularIdle();

        

        wait.until(ExpectedConditions.visibilityOfElementLocated(rname))
                .sendKeys("Name " + randomName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(rdesc))
                .sendKeys("Desc " + randomName);

        selectFromMatDropdownByIndex(selectEquipment, 0);
        selectFromMatDropdownByIndex(property, 0);
        selectFromMatDropdownByIndex(spaOutlets, 0);
        driver.findElement(ranclick).sendKeys(Keys.ESCAPE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantity))
                .sendKeys(randomNumeric);

        waitForAngularIdle();

        WebElement create =
                wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
        jsClick(create);
        //waitForAngularIdle();
 
}

public String getCreatedResources() {
    return CreatedResources.trim();
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
