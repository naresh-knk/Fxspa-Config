package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BillSeriesSetupPage extends BasePage {

    // Locators
    private By outlet =
            By.xpath("(//mat-select[@name='outlet'])[2]");

    private By kotNumber =
            By.xpath("//input[@formcontrolname='kotNumber']");

    private By billNumber =
            By.xpath("//input[@formcontrolname='billNumber']");

    private By startDateInput =
            By.xpath("//input[@formcontrolname='startDate']");

    private By calendarButton =
            By.xpath(
                    "//input[@formcontrolname='startDate']"
                            + "/ancestor::mat-form-field"
                            + "//mat-datepicker-toggle/button"
            );

    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    private static int dateIncrement = 4;

    // Constructor
    public BillSeriesSetupPage(WebDriver driver) {
        super(driver);
    }

    // Common JS Click
    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element
                );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // Dropdown Selection
    private void selectFromMatDropdownByIndex(
            By dropdown,
            int index
    ) {

        WebElement dd =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                dropdown
                        )
                );

        jsClick(dd);

        By option =
                By.xpath(
                        "(//mat-option//span)["
                                + (index + 1)
                                + "]"
                );

        WebElement opt =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                option
                        )
                );

        jsClick(opt);
    }

    // ===== CREATE BILL SERIES =====

    public void createBillSeries() {

        String uniqueValue =
                String.valueOf(
                        System.currentTimeMillis()
                ).substring(7);

        // Select Outlet
        selectFromMatDropdownByIndex(outlet, 0);

        // KOT Number
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        kotNumber
                )
        ).sendKeys(uniqueValue);

        // Open Calendar
        WebElement calendar =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                calendarButton
                        )
                );

        jsClick(calendar);

        // Enabled Dates
        java.util.List<WebElement> enabledDates =
                wait.until(
                        ExpectedConditions
                                .presenceOfAllElementsLocatedBy(
                                        By.xpath(
                                                "//td[@role='gridcell'"
                                                        + " and not("
                                                        + "contains(@class,"
                                                        + "'mat-calendar-body-disabled'))]"
                                        )
                                )
                );

        // Dynamic Date Selection
        int indexToSelect =
                Math.min(
                        dateIncrement,
                        enabledDates.size() - 1
                );

        WebElement date =
                enabledDates.get(indexToSelect);

        jsClick(date);

        // Increase Date Counter
        dateIncrement++;

        // Bill Number
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        billNumber
                )
        ).sendKeys(uniqueValue);

        // Save
        WebElement create =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                createButton
                        )
                );

        jsClick(create);
    }

    // ===== RESET BILL SERIES =====

    public void resetBillSeries() {

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        // Select Outlet
        selectFromMatDropdownByIndex(outlet, 0);

        // KOT Number
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        kotNumber
                )
        ).sendKeys(randomNumeric);

        // Bill Number
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        billNumber
                )
        ).sendKeys(randomNumeric);

        // Reset
        WebElement reset =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                resetButton
                        )
                );

        jsClick(reset);
    }

    // ===== TOAST MESSAGE =====

    public String getToastMsg() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        for (int i = 0; i < 20; i++) {

            String toast =
                    (String) js.executeScript(
                            "var e=document.querySelector('#toast-popup p');"
                                    + "return e?e.innerText:'';"
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

    // ===== RESET VALIDATIONS =====

    public String getBillNumberValue() {

        String value =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        billNumber
                                )
                ).getAttribute("value");

        return value == null
                ? ""
                : value.trim();
    }

    public String getKOTNumberValue() {

        String value =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        kotNumber
                                )
                ).getAttribute("value");

        return value == null
                ? ""
                : value.trim();
    }

    public String getOutletValue() {

        String value =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        outlet
                                )
                ).getText();

        return value == null
                ? ""
                : value.trim();
    }
}