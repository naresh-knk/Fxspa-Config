package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ServiceCategoriesPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By fxSPAConfigIcon =
            By.xpath("//div[contains(@title,'FX SPA Configuration (FX SPA)')]");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By serviceLine =
            By.xpath("//input[@placeholder='Service Line']");

    private By tax =
            By.xpath("//mat-select[@formcontrolname='Tax']");

    private By hsnCode =
            By.xpath("//input[contains(@placeholder,'HSN Code')]");

    private By desc =
            By.xpath("//input[contains(@placeholder,'Description')]");

    private String serviceCategories;

    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

        private By incomeHead=By.xpath("//span[text()='Income Head']");

    // ===== Constructor =====
    public ServiceCategoriesPage(WebDriver driver) {

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

    public void createServiceCategories() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        serviceLine))
                .sendKeys("Name " + randomName);

        selectFromMatDropdownByIndex(tax, 0);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        hsnCode))
                .sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        desc))
                .sendKeys("Desc " + randomName);

        if (driver.findElements(incomeHead).size() > 0 &&
        driver.findElement(incomeHead).isDisplayed()) {

        selectFromMatDropdownByIndex(incomeHead, 0);
}

        waitForAngularIdle();

        WebElement create =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                createButton));

        jsClick(create);
    }

    public void resetServiceCategories() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        serviceLine))
                .sendKeys("Name " + randomName);

        selectFromMatDropdownByIndex(tax, 0);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        hsnCode))
                .sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        desc))
                .sendKeys("Desc " + randomName);

        waitForAngularIdle();

        WebElement reset =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                resetButton));

        jsClick(reset);
    }

    public String getServiceCategories() {

        return serviceCategories;
    }

    public String getToastMsg() {

        By toastMessage =
                By.xpath("//div[@id='toast-popup']//p");

        try {

            WebElement toast =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    toastMessage));

            return toast.getText().trim();

        } catch (Exception e) {

            return "";
        }
    }

    // ===== Reset Validation Getters =====

    public String getServiceNameValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        serviceLine))
                .getAttribute("value")
                .trim();
    }

    public String getServiceDescriptionValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        desc))
                .getAttribute("value")
                .trim();
    }

    public String getServiceHSNCodeValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        hsnCode))
                .getAttribute("value")
                .trim();
    }
}