package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class PaginationPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ===== PAGINATION =====
    private By nextBtn =
            By.xpath("//button[@aria-label='Next page']");

    private By prevBtn =
            By.xpath("//button[@aria-label='Previous page']");

    private By rangeLabel =
            By.xpath("//div[contains(@class,'mat-paginator-range-label')]");

    // ===== ITEMS PER PAGE =====
    private By itemsPerPageDropdown =
            By.xpath("//mat-select[@aria-label='Items per page:']");

    private By rows =
            By.xpath("//table//tbody/tr | //mat-row");

    // ===== COMMON =====
    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    public PaginationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

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
                .executeScript("arguments[0].click();", element);
    }

    // ===== ITEMS PER PAGE =====

    public void selectItemsPerPage(String value) {

        waitForAngularIdle();

        jsClick(wait.until(ExpectedConditions.elementToBeClickable(itemsPerPageDropdown)));

        By option = By.xpath(
                "//div[contains(@class,'cdk-overlay-pane')]//span[normalize-space()='" + value + "']"
        );

        jsClick(wait.until(ExpectedConditions.elementToBeClickable(option)));

        waitForAngularIdle();
    }

    public void validateItemsPerPage(int expected) {

        waitForAngularIdle();

        List<WebElement> rowList = driver.findElements(rows);
        rowList.remove(0);
        if (rowList.isEmpty()) {
            throw new AssertionError("No rows found in table!");
        }

        //  Row count check
        if (rowList.size() > expected) {
            throw new AssertionError(
                    "Expected max rows: " + expected + " but found: " + rowList.size()
            );
        }

        //  Range label check
        String label = driver.findElement(rangeLabel).getText();

        if (!label.contains(String.valueOf(expected))) {
            throw new AssertionError("Range label not updated correctly: " + label);
        }
    }

    // ===== PAGINATION (NEXT / PREVIOUS) =====

    public void validatePagination() {

    waitForAngularIdle();

    String firstPageLabel = driver.findElement(rangeLabel).getText();

    int firstPageRows = getVisibleRowCount();

    // If next button exists & enabled
    if (driver.findElements(nextBtn).size() > 0 &&
            driver.findElement(nextBtn).isEnabled()) {

        // ===== NEXT =====
        jsClick(driver.findElement(nextBtn));
        waitForAngularIdle();

        String nextPageLabel = driver.findElement(rangeLabel).getText();
        int nextPageRows = getVisibleRowCount();

        // Label didn’t change
        if (firstPageLabel.equals(nextPageLabel)) {
            throw new AssertionError("Next button not working (label same)");
        }

        // Rows didn’t change
        if (firstPageRows == nextPageRows) {
            System.out.println("Warning: Row count same, but still checking navigation");
        }

        // ===== PREVIOUS =====
        jsClick(wait.until(ExpectedConditions.elementToBeClickable(prevBtn)));
        waitForAngularIdle();

        String previousPageLabel = driver.findElement(rangeLabel).getText();
        int previousRows = getVisibleRowCount();

        // Didn’t return to first page
        if (!previousPageLabel.equals(firstPageLabel)) {
            throw new AssertionError("Previous button not working");
        }

        if (previousRows != firstPageRows) {
            throw new AssertionError("Previous page row mismatch");
        }

    } else {
        System.out.println("Single page - pagination not applicable");
    }
}

private int getVisibleRowCount() {

    List<WebElement> rowList = driver.findElements(rows);

    int visible = 0;

    for (WebElement row : rowList) {
        if (row.isDisplayed()) {
            visible++;
        }
    }

    // 👉 If we got more rows than expected page size,
    // assume 1 extra non-data row and subtract
    int pageSize = getPageSizeFromLabel(); // e.g., 5 from "1 - 5 of 251"

    if (visible > pageSize) {
        visible = visible - 1;
    }

    return visible;
}

private int getPageSizeFromLabel() {

    String label = driver.findElement(rangeLabel).getText(); 
    // example: "1 - 5 of 251"

    String[] parts = label.split("of")[0].trim().split("-");

    int start = Integer.parseInt(parts[0].trim());
    int end = Integer.parseInt(parts[1].trim());

    return (end - start + 1); // gives page size
}
}