package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NegativeResourcesPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public NegativeResourcesPage(WebDriver driver) {
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
    private By ResourcesName= By.xpath("//input[@placeholder='Resource Name']");
    private By ResourcesDesc= By.xpath("//input[contains(@placeholder,'Description')]");
    // Specific error locators (ADDED)
    private By ResourcesNameError = By.xpath("//mat-error[text()=' Resource Name Required ']");
    private By ResourcesDescError = By.xpath("//mat-error[text()=' Description Required ']");
    // Upload photo
    private By uploadInput = By.xpath("//input[@type='file']");

    // ===== Actions =====

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickFirstTwoFieldsWithoutData() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ResourcesName)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ResourcesDesc)).click();    }

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

    // ===== NEW METHOD (for first 2 fields only) =====
    public String getFirstTwoFieldErrors() {

        String nameErr = wait.until(ExpectedConditions.visibilityOfElementLocated(ResourcesNameError)).getText();

        String descErr = wait.until(ExpectedConditions.visibilityOfElementLocated(ResourcesDescError)).getText();

        return nameErr + " | " + descErr;
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