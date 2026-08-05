package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PackagePage extends BasePage {

    // Unique Package Page Form Locators
    private By pCode =
            By.xpath("//input[@placeholder='Package Code*']");

    private By pName =
            By.xpath("//input[contains(@placeholder,'Package Name*')]");

    private By days =
            By.xpath("//input[contains(@placeholder,'Days')]");

    private By hsnCode =
            By.xpath("//input[contains(@placeholder,'HSN Code*')]");

    private By category =
            By.xpath("(//div[contains(@class,'mat-select-trigger')])[7]");

    private By checkBox =
            By.xpath("(//div[contains(@class,'checkbox cat-title')]//label[contains(@class,'mat-checkbox-layout')])[1]");

    private By ok =
            By.xpath("//button[text()=' OK ']");

    private By ranclick =
            By.xpath("(//mat-option[@role='option'])[1]");

    private By property =
            By.xpath("//span[text()='Property']");

    private By spaOutlets =
            By.xpath("//span[text()='Spa Outlets']");

    private By prefix =
            By.xpath("//input[@placeholder='Prefix*']");

    private By series =
            By.xpath("//input[@placeholder='Start Series*']");

    private By sufix =
            By.xpath("//input[@placeholder='Sufix*']");

    private By tax =
            By.xpath("//span[text()='Tax*']");

    private By startCal =
            By.xpath("(//button[@class='mat-icon-button'])[1]");

    private By endCal =
            By.xpath("(//button[@aria-label='Open calendar'])[2]");

    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    private String createdPackage;

    // ===== Constructor =====
    public PackagePage(WebDriver driver) {
        super(driver); // Driver and Wait are cleanly managed by BasePage
    }

    // ===== Helper Actions =====
    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    private void selectFromMatDropdownByIndex(By dropdown, int index) {
        WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        jsClick(dd);

        By option = By.xpath("(//mat-option//span)[" + (index + 1) + "]");
        WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(option));
        jsClick(opt);
    }

    // ===== Core Test Actions =====
    public void createPackage() {
        String randomName = RandomStringUtils.randomAlphabetic(4);
        String randomNumeric = RandomStringUtils.randomNumeric(2);
        createdPackage = "Name " + randomName;

        wait.until(ExpectedConditions.visibilityOfElementLocated(pCode)).sendKeys(randomNumeric);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pName)).sendKeys(createdPackage);

        wait.until(ExpectedConditions.elementToBeClickable(startCal)).click();

        LocalDate today = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

        String currentDate = today.format(formatter);
        String endDateValue = endDate.format(formatter);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@aria-label='" + currentDate + "']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(endCal)).click();

        List<WebElement> endDateElement = driver.findElements(By.xpath("//td[@aria-label='" + endDateValue + "']"));

        if (endDateElement.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'mat-calendar-next-button')]"))).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@aria-label='" + endDateValue + "']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(days)).sendKeys(randomNumeric);
        wait.until(ExpectedConditions.visibilityOfElementLocated(hsnCode)).sendKeys(randomNumeric);

        selectFromMatDropdownByIndex(category, 0);

        wait.until(ExpectedConditions.elementToBeClickable(checkBox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ok)).click();

        selectFromMatDropdownByIndex(property, 0);
        selectFromMatDropdownByIndex(spaOutlets, 0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ranclick)).sendKeys(Keys.ESCAPE);

        wait.until(ExpectedConditions.visibilityOfElementLocated(prefix)).sendKeys(randomName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(series)).sendKeys(randomName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sufix)).sendKeys(randomName);

        selectFromMatDropdownByIndex(tax, 0);

        WebElement create = wait.until(ExpectedConditions.elementToBeClickable(createButton));
        jsClick(create);
    }

    public void resetPackage() {
        String randomName = RandomStringUtils.randomAlphabetic(4);
        String randomNumeric = RandomStringUtils.randomNumeric(2);

        wait.until(ExpectedConditions.visibilityOfElementLocated(pCode)).sendKeys(randomNumeric);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pName)).sendKeys("Name " + randomName);

        wait.until(ExpectedConditions.elementToBeClickable(startCal)).click();

        LocalDate today = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

        String currentDate = today.format(formatter);
        String endDateValue = endDate.format(formatter);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@aria-label='" + currentDate + "']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(endCal)).click();

        List<WebElement> endDateElement = driver.findElements(By.xpath("//td[@aria-label='" + endDateValue + "']"));

        if (endDateElement.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'mat-calendar-next-button')]"))).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@aria-label='" + endDateValue + "']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(days)).sendKeys(randomNumeric);
        wait.until(ExpectedConditions.visibilityOfElementLocated(hsnCode)).sendKeys(randomNumeric);

        selectFromMatDropdownByIndex(category, 0);

        wait.until(ExpectedConditions.elementToBeClickable(checkBox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ok)).click();

        selectFromMatDropdownByIndex(property, 0);
        selectFromMatDropdownByIndex(spaOutlets, 0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ranclick)).sendKeys(Keys.ESCAPE);

        wait.until(ExpectedConditions.visibilityOfElementLocated(prefix)).sendKeys(randomName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(series)).sendKeys(randomName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sufix)).sendKeys(randomName);

        selectFromMatDropdownByIndex(tax, 0);

        WebElement reset = wait.until(ExpectedConditions.elementToBeClickable(resetButton));
        jsClick(reset);
    }

    public String getCreatedResources() {
        return createdPackage == null ? "" : createdPackage.trim();
    }

    public String getToastMsg() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 15; i++) {
            String toast = (String) js.executeScript(
                    "var e=document.querySelector('#toast-popup p'); return e?e.innerText:'';"
            );

            if (!toast.isEmpty()) {
                return toast.trim();
            }

            try {
                Thread.sleep(200);
            } catch (Exception ignored) {}
        }
        return "";
    }

    // ===== Reset Validation Getters =====
    public String getPackageNameValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pCode))
                .getAttribute("value")
                .trim();
    }

    public String getPackageDescriptionValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pName))
                .getAttribute("value")
                .trim();
    }
}