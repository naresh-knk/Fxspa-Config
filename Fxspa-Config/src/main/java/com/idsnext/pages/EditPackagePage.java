package com.idsnext.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditPackagePage extends BasePage {

    // Locators
    private By rowclick =
            By.xpath("(//td[contains(text(),'Package')])[1]");

    private By desc =
            By.xpath("//textarea[contains(@placeholder,'Package Description')]");

    private By pCode =
            By.xpath("//input[@placeholder='Package Code*']");

    private By pName =
            By.xpath("//input[contains(@placeholder,'Package Name*')]");

    // Actual date input fields
    private By startDateField =
            By.xpath("(//input[contains(@placeholder,'Start Date')])[1]");

    private By endDateField =
            By.xpath("(//input[contains(@placeholder,'End Date')])[1]");

    private By updateButton =
            By.xpath("//button[contains(text(),' Update ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    private By fxSPAConfigIcon =
            By.xpath("//div[contains(@title,'FX SPA Configuration (FX SPA)')]");

    // Constructor
    public EditPackagePage(WebDriver driver) {
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

    // Click FX SPA Icon
    public void clickFXSPAConfigIcon() {

        WebElement fx =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                fxSPAConfigIcon
                        )
                );

        jsClick(fx);
    }

    // Switch Window
    public void switchWindow() {

        String currentWindow =
                driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {

            if (!windowHandle.equals(currentWindow)) {

                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // Click Existing Package Row
    public void clickRow() {

        WebElement row =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                rowclick
                        )
                );

        jsClick(row);
    }

    // Update Package
    public void updatePackage() {

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        LocalDate today =
                LocalDate.now();

        LocalDate futureDate =
                today.plusDays(2);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String start =
                today.format(formatter);

        String end =
                futureDate.format(formatter);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(pCode)
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(pName)
        );

        // Description
        WebElement descriptionField =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(desc)
                );

        descriptionField.sendKeys(" " + randomNumeric);

        // Start Date
        WebElement startDate =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                startDateField
                        )
                );

        startDate.sendKeys(Keys.CONTROL + "a");
        startDate.sendKeys(start);
        startDate.sendKeys(Keys.TAB);

        // End Date
        WebElement endDate =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                endDateField
                        )
                );

        endDate.sendKeys(Keys.CONTROL + "a");
        endDate.sendKeys(end);
        endDate.sendKeys(Keys.TAB);

        // Update Button
        WebElement updateBtn =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                updateButton
                        )
                );

        Actions actions =
                new Actions(driver);

        actions.doubleClick(updateBtn).perform();
    }

    // Reset Package
    public void resetPackage() {

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        WebElement descriptionField =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                desc
                        )
                );

        descriptionField.sendKeys(" " + randomNumeric);

        WebElement resetBtn =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                resetButton
                        )
                );

        jsClick(resetBtn);
    }

    // Toast Message
    public String getToastMsg() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        for (int i = 0; i < 15; i++) {

            String toast =
                    (String) js.executeScript(
                            "var e=document.querySelector('#toast-popup p');"
                                    + "return e?e.innerText:'';"
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

    // Validation Getters
    public String getPackageCodeValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pCode
                )
        ).getAttribute("value").trim();
    }

    public String getPackageNameValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        pName
                )
        ).getAttribute("value").trim();
    }

    public String getPackageDescriptionValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        desc
                )
        ).getAttribute("value").trim();
    }
}