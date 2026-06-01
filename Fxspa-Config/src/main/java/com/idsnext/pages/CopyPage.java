package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CopyPage extends BasePage {

    private WebDriverWait wait;

    ServicesPage servicePage;

    NavigationPage navPage;

    // ===== LOCATORS =====

    private By firstRowCheckbox =
            By.xpath("(//mat-checkbox)[2]");

    private By copyButton =
            By.xpath("//span[text()='Copy']");

    private By saveButton =
            By.xpath("//button//span[normalize-space()='Save']");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    // ===== CONSTRUCTOR =====

    public CopyPage(WebDriver driver) {

        super(driver);

        this.wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        this.servicePage =
                new ServicesPage(driver);

        this.navPage =
                new NavigationPage(driver);
    }

    // ===== COMMON METHODS =====

    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        element
                );
    }

    // ===== ACTIONS =====

    public void selectFirstRecord() {

        waitForAngularIdle();

        WebElement checkbox =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                firstRowCheckbox
                        )
                );

        jsClick(checkbox);
    }

    public void clickCopy() {

        waitForAngularIdle();

        WebElement copy =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                copyButton
                        )
                );

        jsClick(copy);
    }

    // ===== VALIDATION =====

    public void validateCopyScreenOpened() {

        waitForAngularIdle();

        if (driver.findElements(saveButton).isEmpty()) {

            throw new AssertionError(
                    "Copy screen not opened!"
            );
        }
    }

    // ===== COMPLETE FLOW =====

    public void performCopy() {

        navPage.clickRandom();

        selectFirstRecord();

        clickCopy();

        // validateCopyScreenOpened();
    }
}