package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NegativeServiceCategoryPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public NegativeServiceCategoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== Locators =====
    private By saveButton = By.xpath("//button[contains(text(),' Save ')]");
    private By errorMessages = By.xpath("//mat-error");

    // First 3 fields
    private By ServiceCategoryName = By.xpath("//input[@placeholder='Service Line']");
    private By ServiceCategoryDesc = By.xpath("//input[@placeholder='Description']");
    private By HsnCode = By.xpath("//input[@placeholder='HSN Code']");
    private By ServiceCategoryNameError = By.xpath("//mat-error[text()=' Service Line Required ']");
    private By ServiceCategoryDescError = By.xpath("//mat-error[text()=' Description Required ']");
    private By HsnCodeError = By.xpath("//mat-error[text()=' HSNCode Required ']");

    // ===== Actions =====

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickFieldsWithoutData() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ServiceCategoryName)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ServiceCategoryDesc)).click();  
        wait.until(ExpectedConditions.visibilityOfElementLocated(HsnCode)).click();   }

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

    // ===== NEW METHOD (for first 3 fields only) =====
    public String getFieldErrors() {

        String nameErr = wait.until(ExpectedConditions.visibilityOfElementLocated(ServiceCategoryNameError)).getText();

        String descErr = wait.until(ExpectedConditions.visibilityOfElementLocated(ServiceCategoryDescError)).getText();

        String hsnCodeErr = wait.until(ExpectedConditions.visibilityOfElementLocated(HsnCodeError)).getText();


        return nameErr + " | " + descErr + " | " + hsnCodeErr;
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

}