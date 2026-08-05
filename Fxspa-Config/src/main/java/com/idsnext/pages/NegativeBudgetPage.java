package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NegativeBudgetPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public NegativeBudgetPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== Locators =====

    private By saveButton =
            By.xpath("//button[contains(text(),' Create ')]");

    private By errorMessages =
            By.xpath("//mat-error");

    private By financialYear =
            By.xpath("//input[@placeholder='Financial Year']");

    // ===== Actions =====

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    // ===== Trigger Validation =====

   public void triggerValidation() {

    // Wait for toast popup to disappear
    try {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("toast-popup")
        ));

    } catch (Exception ignored) {}

    WebElement yearField =
            wait.until(ExpectedConditions.visibilityOfElementLocated(financialYear));

    // Scroll to field
    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            yearField
    );

    // JS Click
    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            yearField
    );

    // Trigger validation
    yearField.sendKeys(Keys.TAB);

    clickSave();
}
    // ===== Validation Checks =====

    public boolean isValidationDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMessages)
            ).size() > 0;

        } catch (TimeoutException e) {

            return false;
        }
    }

    public String getAllValidationMessages() {

        StringBuilder errors = new StringBuilder();

        for (WebElement el : driver.findElements(errorMessages)) {

            errors.append(el.getText().trim())
                    .append(" | ");
        }

        return errors.toString();
    }

    public String getFieldErrors() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(errorMessages)
            ).getText().trim();

        } catch (TimeoutException e) {

            return "No validation message displayed";
        }
    }

    // ===== Toast Message =====

    public String getToastMessage() {
        return getToastMsg();
    }

    public String getToastMsg() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int maxRetries = 40;
        int delay = 200;

        for (int i = 0; i < maxRetries; i++) {

            String toast = (String) js.executeScript(
                    "var e=document.querySelector('#toast-popup p');" +
                    "return (e && e.offsetParent !== null) ? e.innerText.trim() : '';"
            );

            if (toast != null && !toast.isEmpty()) {
                return toast;
            }

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return "";
    }
}