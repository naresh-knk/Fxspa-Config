package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NegativeServicePage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public NegativeServicePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== Locators =====
     private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");
    private By saveButton = By.xpath("//button[contains(text(),' Save ')]");
    private By errorMessages = By.xpath("//mat-error");

    // First 3 fields
    private By serviceName = By.xpath("//input[@placeholder='Service Name']");
    private By serviceDesc = By.xpath("//input[contains(@placeholder,'Service Description')]");
    private By duration = By.xpath("//input[contains(@placeholder,'Duration(Minutes)')]");

    // Specific error locators (ADDED)
    private By serviceNameError = By.xpath("//mat-error[text()=' Serivce Name Required! ']");
    private By serviceDescError = By.xpath("//mat-error[text()=' Serivce Description Required! ']");
    private By durationError = By.xpath("//mat-error[text()=' Duration Required! ']");

    // Upload photo
    private By uploadInput = By.xpath("//input[@type='file']");

    // ===== Actions =====

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickFirstThreeFieldsWithoutData() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(serviceName)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(serviceDesc)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(duration)).click();
    }

    public boolean isValidationDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMessages)).size() > 0;
    }

    public String getAllValidationMessages() {
        StringBuilder errors = new StringBuilder();
        for (WebElement el : driver.findElements(errorMessages)) {
            errors.append(el.getText()).append(" | ");
        }
        return errors.toString();
    }

    private void waitForAngularIdle() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception ignored) {}

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayBackdrop));
        } catch (Exception ignored) {}
    }

    // ===== NEW METHOD (for first 3 fields only) =====
    public String getFirstThreeFieldErrors() {

        String nameErr = wait.until(ExpectedConditions.visibilityOfElementLocated(serviceNameError)).getText();

        String descErr = wait.until(ExpectedConditions.visibilityOfElementLocated(serviceDescError)).getText();

        String durErr = wait.until(ExpectedConditions.visibilityOfElementLocated(durationError)).getText();

        return nameErr + " | " + descErr + " | " + durErr;
    }

    public void uploadInvalidFile(String filePath) {
        waitForAngularIdle();
        driver.findElement(uploadInput).sendKeys(filePath);
    }

    // ===== FIXED METHOD =====
    public String getToastMessage() {
        return getToastMsg(); // using your JS method
    }

    public String getToastMsg() {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    int maxRetries = 40;          // increased wait time (~8 seconds)
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

private By toastMessage = By.xpath("//div[@id='toast-popup']//p");

public String getToastMsgError() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage))
                .getText()
                .trim();
    } catch (Exception e) {
        return "";
    }
}

}