package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By fxOperationIcon =
            By.xpath("//div[contains(@title,'FX SPA (FX SPA)')]");

    private By randomIcon =
            By.xpath("//i[contains(@class,'fa fa-random')]");

    private By Dashboard =
            By.xpath("//span[normalize-space()='Dashboard']");

    private By calendar =
            By.xpath("(//button[@aria-label='Open calendar'])[1]");

    private By generateButton =
            By.xpath("//button[contains(text(),'Generate Report')]");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By reportTable =
            By.xpath("(//tr[1])[1]");

    private By leftArrow =
            By.xpath("//*[@id='mat-datepicker-0']/mat-calendar-header/div/div/button[2]");

    private By clearButton =
            By.xpath("//button[@aria-label='Clear']");

    // ===== REPORT NAMES =====

    public static final String APPOINTMENTS = "Appointments";
    public static final String SALES = "Sales";
    public static final String TOP_SELLING = "Top Selling";
    public static final String STAFF_UTILIZATION = "Staff Utilization";
    public static final String ROOM_UTILIZATION = "Room Utilization";
    public static final String BUSINESS_REFUSAL = "Business Refusal";
    public static final String COMPLIMENTARY = "Complimentary";
    public static final String RESIDENT = "Resident";

    // ===== Constructor =====

    public DashboardPage(WebDriver driver) {

        super(driver);

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== COMMON METHODS =====

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

    // ===== DYNAMIC LOCATORS =====

    private By lastMonthButton(String reportName) {

        return By.xpath(
                "//h3[contains(normalize-space(),'" + reportName + "')]" +
                "/parent::div/following::button[normalize-space()='Last Month'][1]"
        );
    }

    private By breakupViewButton(String reportName) {

        return By.xpath(
                "//h3[contains(normalize-space(),'" + reportName + "')]" +
                "/parent::div/following::button[contains(@class,'breakup-btn')][1]"
        );
    }

    // ===== TAB SWITCH =====

    public void switchToLatestTab() {

        WebDriverWait tabWait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        tabWait.until(d -> driver.getWindowHandles().size() > 1);

        List<String> tabs =
                new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(tabs.size() - 1));

        System.out.println("Switched to latest tab");
    }

    // ===== ACTIONS =====

    public void clickFXOperations() {

        waitForAngularIdle();

        WebElement fx =
                wait.until(ExpectedConditions.elementToBeClickable(fxOperationIcon));

        jsClick(fx);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickRandom() {

        waitForAngularIdle();

        WebElement random =
                wait.until(ExpectedConditions.presenceOfElementLocated(randomIcon));

        jsClick(random);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForAngularIdle();
    }

    public void clickDashboard() {

        waitForAngularIdle();

        WebElement element =
                wait.until(ExpectedConditions.presenceOfElementLocated(Dashboard));

        jsClick(element);

        waitForAngularIdle();
    }

    public void waitDashboardLoaded() {

        By dashboardContainer =
                By.xpath("//div[contains(@class,'dash-btn-content')]");

        wait.until(ExpectedConditions.presenceOfElementLocated(dashboardContainer));

        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardContainer));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ===== CLICK LAST MONTH =====

    public boolean clickLastMonth(String reportName) {

        try {

            waitForAngularIdle();

            WebElement element =
                    wait.until(ExpectedConditions.presenceOfElementLocated(
                            lastMonthButton(reportName)));

            jsClick(element);

            waitForAngularIdle();

            System.out.println("Clicked Last Month for: " + reportName);

            return true;

        } catch (Exception e) {

            System.out.println("Last Month Button Not Found for: " + reportName);

            return false;
        }
    }

    // ===== CLICK BREAKUP VIEW =====

    public boolean clickBreakupView(String reportName) {

        try {

            waitForAngularIdle();

            WebElement element =
                    wait.until(ExpectedConditions.presenceOfElementLocated(
                            breakupViewButton(reportName)));

            jsClick(element);

            waitForAngularIdle();

            System.out.println("Clicked Breakup View for: " + reportName);

            return true;

        } catch (Exception e) {

            System.out.println("Breakup View Not Found for: " + reportName);

            return false;
        }
    }

    // ===== CLEAR BUTTON =====

    public void clickClearButton() {

        waitForAngularIdle();

        WebElement clear =
                wait.until(ExpectedConditions.elementToBeClickable(clearButton));

        jsClick(clear);

        waitForAngularIdle();

        System.out.println("Clicked Clear Button");
    }

    // ===== CALENDAR =====

    public void clickCalender() {

        waitForAngularIdle();

        wait.until(ExpectedConditions.elementToBeClickable(calendar)).click();
    }

    public void clickCalenderDate() {

        waitForAngularIdle();

        By targetDate =
                By.xpath("//button[@aria-label='1 April 2026']");

        for (int i = 0; i < 24; i++) {

            if (driver.findElements(targetDate).size() > 0) {

                WebElement date =
                        wait.until(ExpectedConditions.elementToBeClickable(targetDate));

                jsClick(date);

                System.out.println("Date Found And Selected");

                return;
            }

            WebElement previousArrow =
                    wait.until(ExpectedConditions.elementToBeClickable(leftArrow));

            jsClick(previousArrow);

            waitForAngularIdle();
        }

        throw new NoSuchElementException(
                "Could not find target date"
        );
    }

    // ===== GENERATE REPORT =====

    public void clickGenerate() {

        waitForAngularIdle();

        wait.until(ExpectedConditions.elementToBeClickable(generateButton)).click();

        waitForAngularIdle();
    }

    // ===== VALIDATION =====

    public boolean isReportGenerated() {

        return isElementVisible(reportTable);
    }
}