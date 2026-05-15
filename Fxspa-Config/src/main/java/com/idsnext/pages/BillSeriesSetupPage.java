package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BillSeriesSetupPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By fxSPAConfigIcon =
            By.xpath("//div[contains(@title,'FX SPA Configuration (FX SPA)')]");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By randomIcon =
            By.xpath("/html/body/app-root/div[1]/nav/ul/li/a/span[1]");

    private By billSeries =
            By.xpath("//span[text()=' Bill Series Setup ']");

    private By plusButton =
            By.xpath("//button[normalize-space()='+']");

    private By outlet =
            By.xpath("(//mat-select[@name='outlet'])[2]");

    private By kotNumber =
            By.xpath("//input[@formcontrolname='kotNumber']");

    private By billNumber =
            By.xpath("//input[@formcontrolname='billNumber']");

    private By startDateInput =
            By.xpath("//input[@formcontrolname='startDate']");

    private By calendarButton =
            By.xpath("//input[@formcontrolname='startDate']/ancestor::mat-form-field//mat-datepicker-toggle/button");

    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    private String createdBillSeries;

    // ===== Constructor =====

    public BillSeriesSetupPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== Common Waits =====

    private void waitForAngularIdle() {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception ignored) {
        }

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayBackdrop));
        } catch (Exception ignored) {
        }
    }

    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    private void selectFromMatDropdownByIndex(By dropdown, int index) {

        waitForAngularIdle();

        WebElement dd =
                wait.until(ExpectedConditions.elementToBeClickable(dropdown));

        jsClick(dd);

        By option =
                By.xpath("(//mat-option//span)[" + (index + 1) + "]");

        WebElement opt =
                wait.until(ExpectedConditions.elementToBeClickable(option));

        jsClick(opt);

        waitForAngularIdle();
    }

    // ===== Actions =====

    public void clickFXSPAConfigIcon() {

        waitForAngularIdle();

        WebElement fx =
                wait.until(ExpectedConditions.elementToBeClickable(fxSPAConfigIcon));

        jsClick(fx);

        waitForAngularIdle();
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

        wait.until(ExpectedConditions.elementToBeClickable(randomIcon)).click();
    }

    public void clickBillSeries() {

        waitForAngularIdle();

        wait.until(ExpectedConditions.elementToBeClickable(billSeries)).click();

        waitForAngularIdle();
    }

    public void clickAdd() {

        waitForAngularIdle();

        WebElement plus =
                wait.until(ExpectedConditions.elementToBeClickable(plusButton));

        jsClick(plus);
    }

    // ===== CREATE =====

    public void createBillSeries() {

        String randomNumeric = RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        // ===== Outlet =====

        selectFromMatDropdownByIndex(outlet, 0);

        // ===== KOT =====

        wait.until(ExpectedConditions.visibilityOfElementLocated(kotNumber))
                .sendKeys(randomNumeric);

        // ===== Date Selection =====

        waitForAngularIdle();

        WebElement calendar =
                wait.until(ExpectedConditions.visibilityOfElementLocated(calendarButton));

        jsClick(calendar);

        // Select first enabled date
        By enabledDate =
                By.xpath("(//td[@role='gridcell' and not(contains(@class,'mat-calendar-body-disabled'))])[1]");

        WebElement date =
                wait.until(ExpectedConditions.elementToBeClickable(enabledDate));

        jsClick(date);

        // Wait for popup close
        try {

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".cdk-overlay-pane")
            ));

        } catch (Exception ignored) {
        }

        System.out.println(
                "Selected Date: "
                        + driver.findElement(startDateInput)
                        .getAttribute("value")
        );

        // ===== Bill Number =====

        wait.until(ExpectedConditions.visibilityOfElementLocated(billNumber))
                .sendKeys(randomNumeric);

        waitForAngularIdle();

        // ===== Save =====

        WebElement create =
                wait.until(ExpectedConditions.elementToBeClickable(createButton));

        jsClick(create);
    }

    // ===== RESET =====

    public void resetBillSeries() {

        String randomNumeric = RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        selectFromMatDropdownByIndex(outlet, 0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(kotNumber))
                .sendKeys(randomNumeric);

        wait.until(ExpectedConditions.visibilityOfElementLocated(billNumber))
                .sendKeys(randomNumeric);

        WebElement reset =
                wait.until(ExpectedConditions.elementToBeClickable(resetButton));

        jsClick(reset);
    }

    // ===== Toast =====

    public String getToastMsg() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 20; i++) {

            String toast = (String) js.executeScript(
                    "var e=document.querySelector('#toast-popup p');" +
                            "return e?e.innerText:'';"
            );

            if (!toast.isEmpty()) {
                return toast.trim();
            }

            try {
                Thread.sleep(300);
            } catch (Exception ignored) {
            }
        }

        return "";
    }

    // ===== Reset Validation =====

    public String getBillNumberValue() {

        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(billNumber))
                .getAttribute("value");

        return value == null ? "" : value.trim();
    }

    public String getKOTNumberValue() {

        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(kotNumber))
                .getAttribute("value");

        return value == null ? "" : value.trim();
    }

    public String getOutletValue() {

        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(outlet))
                .getText();

        return value == null ? "" : value.trim();
    }
}