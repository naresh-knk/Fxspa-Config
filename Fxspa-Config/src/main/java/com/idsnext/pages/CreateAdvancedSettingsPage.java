package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateAdvancedSettingsPage extends BasePage {

    // Locators
    private By operationName =
            By.xpath(
                    "//mat-label[normalize-space()='Operation Name']"
                            + "/ancestor::mat-form-field//input"
            );

    private By operationID =
            By.xpath(
                    "//mat-label[normalize-space()='Operation ID']"
                            + "/ancestor::mat-form-field//input"
            );

    private By desc =
            By.xpath(
                    "//mat-label[normalize-space()='Description']"
                            + "/ancestor::mat-form-field//input"
            );

    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    // Constructor
    public CreateAdvancedSettingsPage(WebDriver driver) {
        super(driver);
    }

    // Common JS Click
    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element
                );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // Create Advanced Settings
    public void createAdvancedSettings() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        String randomNumeric =
                RandomStringUtils.randomNumeric(3);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        operationName
                )
        ).sendKeys("Op" + randomName);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        operationID
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        desc
                )
        ).sendKeys("Desc " + randomName);

        WebElement create =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                createButton
                        )
                );

        jsClick(create);
    }

    // Reset Advanced Settings
    public void resetAdvancedSettings() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        operationName
                )
        ).sendKeys("Name " + randomName);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        operationID
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        desc
                )
        ).sendKeys("Desc " + randomName);

        WebElement reset =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                resetButton
                        )
                );

        jsClick(reset);
    }

    // Toast Message
    public String getToastMsg() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        for (int i = 0; i < 15; i++) {

            String toast =
                    (String) js.executeScript(
                            "var e=document.querySelector('#toast-popup p');"
                                    + " return e?e.innerText:'';"
                    );

            if (!toast.isEmpty()) {
                return toast.trim();
            }

            try {
                Thread.sleep(200);
            } catch (Exception ignored) {
            }
        }

        return "";
    }

    // Reset Validation Getters
    public String getAdvanceSettingsNameValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        operationName
                )
        ).getAttribute("value").trim();
    }

    public String getAdvanceSettingsDescriptionValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        desc
                )
        ).getAttribute("value").trim();
    }
}