package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditProductsPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By fxSPAConfigIcon =
            By.xpath("//div[contains(@title,'FX SPA Configuration (FX SPA)')]");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By rowclick =
            By.xpath("(//td[contains(text(),'Name')])[1]");

    private By rname =
            By.xpath("//input[@placeholder='Product Name']");

    private By rdesc =
            By.xpath("//input[contains(@placeholder,'Product Description')]");

    private By sdesc =
            By.xpath("//input[contains(@placeholder,'Short Description')]");

    private String updateProductName;

    private By updateButton =
            By.xpath("//button[contains(text(),' Update ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    // ===== Constructor =====
    public EditProductsPage(WebDriver driver) {

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

    public void clickRow() {

        waitForAngularIdle();

        WebElement plus =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                rowclick));

        jsClick(plus);
    }

    public void editProducts() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        waitForAngularIdle();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(rname))
                .sendKeys("Name " + randomName);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(rdesc))
                .sendKeys("Desc " + randomName);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(sdesc))
                .sendKeys("Desc " + randomName);

        waitForAngularIdle();

        WebElement create =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                updateButton));

        jsClick(create);
    }

    public void resetProducts() {

        waitForAngularIdle();

        WebElement reset =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                resetButton));

        jsClick(reset);
    }

    public String getUpdateProductName() {

        return updateProductName;
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

    public String getProductNameValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(rname))
                .getAttribute("value")
                .trim();
    }

    public String getProductDescriptionValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(rdesc))
                .getAttribute("value")
                .trim();
    }
}