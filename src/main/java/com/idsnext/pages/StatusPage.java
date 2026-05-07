package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StatusPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ===== Correct Locators =====

    // FIXED: Proper dropdown (not span)
    private By statusDropdown =
            By.xpath("//mat-select[@aria-label='Status']");

    // Overlay-safe options
    private By activeOption =
            By.xpath("//div[contains(@class,'cdk-overlay-pane')]//span[normalize-space()='Active']");

    private By inactiveOption =
            By.xpath("//div[contains(@class,'cdk-overlay-pane')]//span[normalize-space()='Inactive']");

    private By allOption =
            By.xpath("//div[contains(@class,'cdk-overlay-pane')]//span[normalize-space()='All']");

    private By toggles =
            By.xpath("//mat-slide-toggle//input[@type='checkbox']");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By nextButton =
    By.xpath("//button[@aria-label='Next page' and not(@disabled)]");

    // ===== Constructor =====

    public StatusPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== Wait =====

    private void waitForAngularIdle() {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception ignored) {}

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayBackdrop));
        } catch (Exception ignored) {}
    }

    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // ===== Get Current Selected Status =====

    public String getSelectedStatus() {

        WebElement selected =
                driver.findElement(By.xpath("//mat-select//span[contains(@class,'mat-select-value-text')]"));

        return selected.getText().trim().toLowerCase();
    }

    // ===== Select Status (Smart) =====

    public void selectStatus(String status) {

    waitForAngularIdle();

    // Step 0: Skip if already selected
    try {
        String current = driver.findElement(
                By.xpath("//mat-select//span[contains(@class,'mat-select-value-text')]")
        ).getText().trim();

        if (current.equalsIgnoreCase(status)) {
            return;
        }
    } catch (Exception ignored) {}

    // Step 1: Open dropdown
    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
    jsClick(dropdown);

    // Step 2: Wait for overlay to appear
    wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class,'cdk-overlay-pane')]")
    ));

    // Step 3: Dynamic option locator (NO switch needed)
   By optionLocator = By.xpath(
    "//div[contains(@class,'cdk-overlay-pane')]//span[" +
    "translate(normalize-space(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" 
    + status.toLowerCase() + "']"
);

    // Step 4: Wait and click
    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
    jsClick(option);

    waitForAngularIdle();
}

    // ===== Validation =====

    public void validateStatus(String expectedStatus) {

    waitForAngularIdle();

    boolean hasActive = false;
    boolean hasInactive = false;

    // 👉 Loop for pagination (only useful for "all")
    while (true) {

        List<WebElement> toggleList = driver.findElements(toggles);

        if (toggleList.isEmpty()) {
            throw new AssertionError("No records found in table!");
        }

        for (WebElement toggle : toggleList) {

            boolean isChecked = toggle.isSelected();

            if (isChecked) {
                hasActive = true;
            } else {
                hasInactive = true;
            }

            // ✅ Validation for Active filter
            if (expectedStatus.equalsIgnoreCase("active") && !isChecked) {
                throw new AssertionError("Inactive record found in Active filter");
            }

            // ✅ Validation for Inactive filter
            if (expectedStatus.equalsIgnoreCase("inactive") && isChecked) {
                throw new AssertionError("Active record found in Inactive filter");
            }
        }

        // ✅ If not "all", no need pagination
        if (!expectedStatus.equalsIgnoreCase("all")) {
            break;
        }

        // ✅ If both found → stop
        if (hasActive && hasInactive) {
            break;
        }

        // 👉 Check next button
        List<WebElement> nextBtns = driver.findElements(nextButton);

        if (nextBtns.isEmpty()) {
            break; // no more pages
        }

        // 👉 Go to next page
        jsClick(nextBtns.get(0));
        waitForAngularIdle();
    }

    // ✅ Final validation for "all"
    if (expectedStatus.equalsIgnoreCase("all")) {
        if (!(hasActive && hasInactive)) {
            throw new AssertionError(
                    "All filter did not show both Active and Inactive records across pages"
            );
        }
    }
}
}