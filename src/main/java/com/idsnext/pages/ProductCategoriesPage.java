package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductCategoriesPage extends BasePage {

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
            By.xpath("//span[normalize-space()='Product Categories']");

            private By plusButton = By.xpath("//button[normalize-space()='+']");

    private By serviceLine= By.xpath("//input[@placeholder='Product Line']");
    private By tax= By.xpath("//mat-select[@formcontrolname='Tax']");
    private By hsnCode= By.xpath("//input[contains(@placeholder,'HSN Code')]");
    // private By pcategory= By.xpath("//span[text()='Product Category']");
    private By desc= By.xpath("//input[contains(@placeholder,'Description')]");
    //private By incomeHead= By.xpath("//mat-select[@formcontrolname='Income']");
    
    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");
            private String productCategories;


    // ===== Constructor =====
    public ProductCategoriesPage(WebDriver driver) {
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
       // Thread.sleep(10000); // Temporary wait to observe the click action, replace with proper wait if needed
    }


    public void clickProductCategories() {
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

    public void createProductCategories() {

        String randomName = RandomStringUtils.randomAlphabetic(4);
        String randomNumeric = RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        

        wait.until(ExpectedConditions.visibilityOfElementLocated(serviceLine))
                .sendKeys("Name " + randomName);
        

        selectFromMatDropdownByIndex(tax, 0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(hsnCode))
                .sendKeys(randomNumeric);
        wait.until(ExpectedConditions.visibilityOfElementLocated(desc))
                .sendKeys("Desc " + randomName);
        //selectFromMatDropdown(incomeHead, "Ayurveda");
        

        waitForAngularIdle();

        WebElement create =
                wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
        jsClick(create);
        //waitForAngularIdle();
    
}

public String getProductCategories() {
    return productCategories.trim();
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