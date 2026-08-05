package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductCategoriesPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By ProductLine =
            By.xpath("//input[@placeholder='Product Line']");

    private By tax =
            By.xpath("//mat-select[@formcontrolname='Tax']");

    private By hsnCode =
            By.xpath("//input[contains(@placeholder,'HSN Code')]");

    private By desc =
            By.xpath("//input[contains(@placeholder,'Description')]");

    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");

    private String productCategories;

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

        private By incomeHead=By.xpath("//span[text()='Income Head']");         

    // ===== Constructor =====
    public ProductCategoriesPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== Common Waits =====

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

    public void createProductCategories() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductLine))
                .sendKeys("Name " + randomName);

        selectFromMatDropdownByIndex(tax, 0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(hsnCode))
                .sendKeys(randomNumeric);

        wait.until(ExpectedConditions.visibilityOfElementLocated(desc))
                .sendKeys("Desc " + randomName);

                if (driver.findElements(incomeHead).size() > 0 &&
        driver.findElement(incomeHead).isDisplayed()) {

        selectFromMatDropdownByIndex(incomeHead, 0);
}

        waitForAngularIdle();

        WebElement create =
                wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));

        jsClick(create);
    }

    public void resetProductCategories() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        waitForAngularIdle();

        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductLine))
                .sendKeys("Name " + randomName);

        selectFromMatDropdownByIndex(tax, 0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(hsnCode))
                .sendKeys(randomNumeric);

        wait.until(ExpectedConditions.visibilityOfElementLocated(desc))
                .sendKeys("Desc " + randomName);

        waitForAngularIdle();

        WebElement reset =
                wait.until(ExpectedConditions.visibilityOfElementLocated(resetButton));

        jsClick(reset);
    }

    public String getProductCategories() {

        return productCategories;
    }

    public String getToastMsg() {

        By toastMessage =
                By.xpath("//div[@id='toast-popup']//p");

        try {

            WebElement toast =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));

            return toast.getText().trim();

        } catch (Exception e) {

            return "";
        }
    }

    // ===== Reset Validation Getters =====

    public String getProductNameValue() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(ProductLine))
                .getAttribute("value")
                .trim();
    }

    public String getProductDescriptionValue() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(desc))
                .getAttribute("value")
                .trim();
    }

    public String getProductHSNCodeValue() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(hsnCode))
                .getAttribute("value")
                .trim();
    }
}