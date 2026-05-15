package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class DashboardButtonPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ===== LOCATORS =====

    private By generateButton =
            By.xpath("//button[contains(text(),'Generate Report')]");

    private By backButton =
            By.xpath("//button[contains(text(),'Back')]");

    private By resetButton =
            By.xpath("//span[text()='Reset']");

    private By exportPdfButton =
            By.xpath("//button[contains(text(),'Export to Pdf')]");

    private By exportExcelButton =
            By.xpath("//button[contains(text(),'Export to Excel')]");

    private By reportRows =
            By.xpath("//table/tbody/tr");

    private By startDate =
            By.xpath("//input[@placeholder='From Date']");

    private By endDate =
            By.xpath("//input[@placeholder='To Date']");

      private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");


    // ===== CONSTRUCTOR =====

    public DashboardButtonPage(WebDriver driver) {

        super(driver);

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== COMMON =====

    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

     private void waitForAngularIdle() {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception ignored) {}

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayBackdrop));
        } catch (Exception ignored) {}
    }

    // ===== ACTIONS =====

    public void clickGenerate() {

        WebElement generate =
                wait.until(ExpectedConditions.elementToBeClickable(generateButton));

        jsClick(generate);
    }

    public void clickBack() {

        WebElement back =
                wait.until(ExpectedConditions.elementToBeClickable(backButton));

        jsClick(back);

        waitForAngularIdle();
    }

    public void clickReset() {
        waitForAngularIdle();

        WebElement reset =
                wait.until(ExpectedConditions.elementToBeClickable(resetButton));

        jsClick(reset);
        waitForAngularIdle();
    }

    public void clickExportPdf() {

        WebElement pdf =
                wait.until(ExpectedConditions.elementToBeClickable(exportPdfButton));

        jsClick(pdf);
    }

    public void clickExportExcel() {

        WebElement excel =
                wait.until(ExpectedConditions.elementToBeClickable(exportExcelButton));

        jsClick(excel);

    }

    // ===== VALIDATIONS =====

    public boolean isReportVisible() {

        return driver.findElements(reportRows).size() > 0;
    }

    public boolean isPdfDownloaded(String downloadPath) {

        File folder = new File(downloadPath);

        File[] files = folder.listFiles();

        if (files == null) {
            return false;
        }

        for (File file : files) {

            if (file.getName().endsWith(".pdf")) {

                return true;
            }
        }

        return false;
    }

    public boolean isExcelDownloaded(String downloadPath) {

        File folder = new File(downloadPath);

        File[] files = folder.listFiles();

        if (files == null) {
            return false;
        }

        for (File file : files) {

            if (file.getName().endsWith(".xlsx")) {

                return true;
            }
        }

        return false;
    }

    public boolean isStartAndEndDateSame() {

        String start =
                driver.findElement(startDate).getAttribute("value");

        String end =
                driver.findElement(endDate).getAttribute("value");

        return start.equals(end);
    }
}

