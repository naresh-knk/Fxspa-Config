package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ===== Common Locators =====

    private By fxSPAConfigIcon =
            By.xpath("//div[contains(@title,'FX SPA Configuration (FX SPA)')]");

    private By randomIcon =
            By.xpath("//i[contains(@class,'fa-random')]");

    private By searchField =
            By.xpath("//input[contains(@placeholder,'Search')]");

    private By searchIcon =
            By.cssSelector("i.fas.fa-search");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    // ===== Constructor =====

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== Common Wait =====

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

    // ===== Generic Actions =====

    public void clickFXSPAConfigIcon() {
        waitForAngularIdle();
        jsClick(wait.until(ExpectedConditions.elementToBeClickable(fxSPAConfigIcon)));
    }

    public void switchWindow() {

        String currentWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void clickRandom() {
        waitForAngularIdle();
        jsClick(wait.until(ExpectedConditions.elementToBeClickable(randomIcon)));
    }

    //  Generic module click
    public void selectModule(By moduleLocator) {
        waitForAngularIdle();
        jsClick(wait.until(ExpectedConditions.elementToBeClickable(moduleLocator)));
    }

    public void search(String text) {
        waitForAngularIdle();

        WebElement field =
                wait.until(ExpectedConditions.elementToBeClickable(searchField));

        field.clear();
        field.sendKeys(text);

        jsClick(wait.until(ExpectedConditions.elementToBeClickable(searchIcon)));

        waitForAngularIdle();
    }

   public void validateColumnValues(String columnName, String searchText) {

    // Try normal table headers first
    List<WebElement> headers = driver.findElements(By.xpath("//table//th"));

    // If not found, try Angular Material headers
    if (headers.isEmpty()) {
        headers = driver.findElements(By.xpath("//mat-header-cell"));
    }

    int columnIndex = -1;

    for (int i = 0; i < headers.size(); i++) {

        String headerText = headers.get(i).getText()
                .replaceAll("\\s+", " ")
                .trim();

        if (headerText.equalsIgnoreCase(columnName)) {
            columnIndex = i + 1;
            break;
        }
    }

    if (columnIndex == -1) {
        throw new RuntimeException(columnName + " column not found!");
    }

    List<WebElement> elements;

    // If normal table
    if (driver.findElements(By.xpath("//table//tbody/tr")).size() > 0) {

        elements = driver.findElements(
                By.xpath("//table//tbody/tr/td[" + columnIndex + "]")
        );

    } else {
        // Angular Material table
        elements = driver.findElements(
                By.xpath("//mat-row/mat-cell[" + columnIndex + "]")
        );
    }

    if (elements.isEmpty()) {
        throw new AssertionError("No data found in table for validation!");
    }

    for (WebElement element : elements) {

        String actual = element.getText().trim().toLowerCase();

        if (!actual.contains(searchText.toLowerCase())) {
            throw new AssertionError(
                    "Mismatch in column [" + columnName + "] -> Expected: "
                            + searchText + " but found: " + actual
            );
        }
    }
}
}
