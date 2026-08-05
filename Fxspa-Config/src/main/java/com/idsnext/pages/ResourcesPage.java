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

    private By rname =
            By.xpath("//input[@placeholder='Resource Name']");

    private By rdesc =
            By.xpath("//input[contains(@placeholder,'Description')]");

    private By selectEquipment =
            By.xpath("//span[text()='Equipment']");

    private By ranclick =
            By.xpath("(//mat-option[@role='option'])[1]");

    private By property =
            By.xpath("//span[text()='Property']");

    private By spaOutlets =
            By.xpath("//span[text()='Spa Outlets']");

    private By quantity =
            By.xpath("//input[@placeholder='Quantity']");

    private String CreatedResources;

    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    // ===== Constructor =====
    public ResourcesPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== Common Waits =====

    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    private void selectFromMatDropdownByIndex(By dropdown, int index) {

        waitForAngularIdle();

        WebElement dd =
                wait.until(
                        ExpectedConditions.elementToBeClickable(dropdown));

        jsClick(dd);

        By option =
                By.xpath("(//mat-option//span)[" + (index + 1) + "]");

        WebElement opt =
                wait.until(
                        ExpectedConditions.elementToBeClickable(option));

        jsClick(opt);

        waitForAngularIdle();
    }

    // ===== Actions =====

    public void clickFXSPAConfigIcon() {

        waitForAngularIdle();

        WebElement fx =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                fxSPAConfigIcon));

        jsClick(fx);

        waitForAngularIdle();
    }

    public void switchWindow() {

        String currentWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {

            if (!windowHandle.equals(currentWindow)) {

                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void createResources() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(rname))
                .sendKeys("Name " + randomName);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(rdesc))
                .sendKeys("Desc " + randomName);

        selectFromMatDropdownByIndex(selectEquipment, 0);
        selectFromMatDropdownByIndex(property, 0);
        selectFromMatDropdownByIndex(spaOutlets, 0);

        driver.findElement(ranclick).sendKeys(Keys.ESCAPE);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantity))
                .sendKeys(randomNumeric);

        waitForAngularIdle();

        WebElement create =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                createButton));

        jsClick(create);
    }

    public void resetResources() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        String randomNumeric =
                RandomStringUtils.randomNumeric(1);

        waitForAngularIdle();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(rname))
                .sendKeys("Name " + randomName);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(rdesc))
                .sendKeys("Desc " + randomName);

        selectFromMatDropdownByIndex(selectEquipment, 0);
        selectFromMatDropdownByIndex(property, 0);
        selectFromMatDropdownByIndex(spaOutlets, 0);

        driver.findElement(ranclick).sendKeys(Keys.ESCAPE);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantity))
                .sendKeys(randomNumeric);

        waitForAngularIdle();

        WebElement reset =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                resetButton));

        jsClick(reset);
    }

    public String getCreatedResources() {
        return CreatedResources;
    }

    public String getToastMsg() {

    By toastMessage =
            By.xpath("//div[@id='toast-popup']//p");

    try {

        WebElement toast =
                wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));

        return toast.getText().trim();

    } catch (Exception e) {

        return "";
    }
}

    // ===== Reset Validation Getters =====

    public String getResourcesNameValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(rname))
                .getAttribute("value")
                .trim();
    }

    public String getResourcesDescriptionValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(rdesc))
                .getAttribute("value")
                .trim();
    }
}