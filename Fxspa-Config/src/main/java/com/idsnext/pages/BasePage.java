package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    public BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================= COMMON WAIT =================

    protected void waitForAngularIdle() {

        try {

            WebDriverWait smallWait =
                    new WebDriverWait(driver, Duration.ofSeconds(15));

            smallWait.until(driver -> {

                List<WebElement> loaders =
                        driver.findElements(
                                By.cssSelector(".loader-outer"));

                for (WebElement loader : loaders) {

                    try {

                        if (loader.isDisplayed()) {
                            return false;
                        }

                    } catch (StaleElementReferenceException ignored) {}
                }

                return true;
            });

        } catch (Exception ignored) {}

        try {

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            overlayBackdrop));

        } catch (Exception ignored) {}
    }

    // ================= GENERIC VISIBILITY =================

    public boolean isElementVisible(By locator) {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }

    // ================= COMMON CLICK =================

    public void click(By locator) {

        waitForAngularIdle();

        WebElement element =
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);

        waitForAngularIdle();
    }

    // ================= COMMON TYPE =================

    public void type(By locator, String text) {

        waitForAngularIdle();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();

        element.sendKeys(text);
    }
}
