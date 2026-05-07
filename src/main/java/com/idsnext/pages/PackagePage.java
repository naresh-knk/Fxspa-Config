package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PackagePage extends BasePage {

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

    private By packages=
            By.xpath("//span[text()=' Packages ']");

            private By plusButton = By.xpath("//button[normalize-space()='+']");

    private By pCode= By.xpath("//input[@placeholder='Package Code*']");
    private By pName= By.xpath("//input[contains(@placeholder,'Package Name*')]");
    private By days= By.xpath("//input[contains(@placeholder,'Days')]");
    private By hsnCode= By.xpath("//input[contains(@placeholder,'HSN Code*')]");
    private By category= By.xpath("(//div[contains(@class,'mat-select-trigger')])[7]");
    private By checkBox= By.xpath("(//div[contains(@class,'checkbox cat-title')]//label[contains(@class,'mat-checkbox-layout')])[1]");
    private By ok= By.xpath("//button[text()=' OK ']");
    private By ranclick= By.xpath("(//mat-option[@role='option'])[1]");
    private By property=By.xpath("//span[text()='Property']");
    private By spaOutlets= By.xpath("//span[text()='Spa Outlets']");
    private By prefix = By.xpath("//input[@placeholder='Prefix*']");
    private By series = By.xpath("//input[@placeholder='Start Series*']");
    private By sufix = By.xpath("//input[@placeholder='Sufix*']");
    private By tax= By.xpath("//span[text()='Tax*']");
    private By startCal=By.xpath("(//button[@class='mat-icon-button'])[1]");
    private By endCal=By.xpath("(//button[@aria-label='Open calendar'])[2]");

    private String createdPackage;
  

    
    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");


    // ===== Constructor =====
    public PackagePage(WebDriver driver) {
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


    public void clickPackage() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(packages)).click();
        waitForAngularIdle();
    }

     public void clickAdd() {
        waitForAngularIdle();

        WebElement plus =
                wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        jsClick(plus);
    }

    public void createPackage() {

    String randomName = RandomStringUtils.randomAlphabetic(4);
    String randomNumeric = RandomStringUtils.randomNumeric(2);

    waitForAngularIdle();

    wait.until(ExpectedConditions.visibilityOfElementLocated(pCode))
            .sendKeys(randomNumeric);

    wait.until(ExpectedConditions.visibilityOfElementLocated(pName))
            .sendKeys("Name " + randomName);

    wait.until(ExpectedConditions.elementToBeClickable(startCal))
            .click();

    LocalDate today = LocalDate.now();
    LocalDate endDate = LocalDate.now().plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

    String currentDate = today.format(formatter);
    String endDateValue = endDate.format(formatter);

    wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[@aria-label='" + currentDate + "']")))
            .click();

    wait.until(ExpectedConditions.elementToBeClickable(endCal))
            .click();

    // check if end date month is visible
    List<WebElement> endDateElement = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//td[@aria-label='" + endDateValue + "']")));

    if (endDateElement.size() == 0) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'mat-calendar-next-button')]")))
                .click();
    }

    wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[@aria-label='" + endDateValue + "']")))
            .click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(days))
            .sendKeys(randomNumeric);

    wait.until(ExpectedConditions.visibilityOfElementLocated(hsnCode))
            .sendKeys(randomNumeric);

    selectFromMatDropdownByIndex(category, 0);

    wait.until(ExpectedConditions.elementToBeClickable(checkBox))
            .click();

    wait.until(ExpectedConditions.elementToBeClickable(ok))
            .click();

    selectFromMatDropdownByIndex(property, 0);
    selectFromMatDropdownByIndex(spaOutlets, 0);

    wait.until(ExpectedConditions.visibilityOfElementLocated(ranclick))
            .sendKeys(Keys.ESCAPE);

    wait.until(ExpectedConditions.visibilityOfElementLocated(prefix))
            .sendKeys(randomName);

    wait.until(ExpectedConditions.visibilityOfElementLocated(series))
            .sendKeys(randomName);

    wait.until(ExpectedConditions.visibilityOfElementLocated(sufix))
            .sendKeys(randomName);

    selectFromMatDropdownByIndex(tax, 0);

    waitForAngularIdle();

    WebElement create = wait.until(
            ExpectedConditions.elementToBeClickable(createButton));

    jsClick(create);
}

public String getCreatedResources() {
    return createdPackage.trim();
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
