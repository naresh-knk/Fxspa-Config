package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends BasePage {

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
            By.xpath("//span[normalize-space()='Products']");

            private By plusButton = By.xpath("//button[normalize-space()='+']");

    private By rname= By.xpath("//input[@placeholder='Product Name']");
    private By measure= By.xpath("//mat-select[@formcontrolname='Mesure']");
    private By rdesc= By.xpath("//input[contains(@placeholder,'Product Description')]");
    private By pcategory= By.xpath("//span[text()='Product Category']");
    private By sdesc= By.xpath("//input[contains(@placeholder,'Short Description')]");
    private By ranclick= By.xpath("(//mat-option[@role='option'])[1]");
    private By property=By.xpath("//span[text()='Property']");
    private By spaOutlets= By.xpath("//span[text()='Spa Outlets']");
    private By retailPrice= By.xpath("//input[contains(@placeholder,'Retail Price')]");
    private By checkBox=By.xpath("//mat-label[text()='Week Days']/following-sibling::*");
    private String createdProductName;  

    
    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");


    // ===== Constructor =====
    public ProductsPage(WebDriver driver) {
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


    public void clickProducts() {
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

    public void createProducts() {

        String randomName = RandomStringUtils.randomAlphabetic(4);
        String randomNumeric = RandomStringUtils.randomNumeric(2);

        createdProductName = "Name " + randomName;
        waitForAngularIdle();

        

        wait.until(ExpectedConditions.visibilityOfElementLocated(rname))
                .sendKeys("Name " + randomName);
                selectFromMatDropdownByIndex(measure, 0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(rdesc))
                .sendKeys("Desc " + randomName);

        selectFromMatDropdownByIndex(pcategory, 0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sdesc))
                .sendKeys("Desc " + randomName);

        selectFromMatDropdownByIndex(property, 0);
        //selectFromMatDropdown(spaOutlets, "Zen Spa");
        selectFromMatDropdownByIndex(spaOutlets, 0);
        driver.findElement(ranclick).sendKeys(Keys.ESCAPE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(retailPrice))
                .sendKeys(randomNumeric);

        waitForAngularIdle();

        WebElement create =
                wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
        jsClick(create);
        waitForAngularIdle();

       
}

 public String getCreatedProductName() {
    return createdProductName;
}

public String getFirstRowProductName() {

    By firstRowProduct = By.xpath("//*[@id=\"maintable\"]/table/tbody/tr[2]/td[2]");

    return wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowProduct))
            .getText()
            .trim();
}
}
