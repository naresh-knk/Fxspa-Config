package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditServiceCategoriesPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By rowclick =
            By.xpath("(//td[contains(text(),'Name')])[1]");

    private By serviceLine =
            By.xpath("//input[@placeholder='Service Line']");

    private By hsnCode =
            By.xpath("//input[contains(@placeholder,'HSN Code')]");

    private By desc =
            By.xpath("//input[contains(@placeholder,'Description')]");

    private By updateButton =
            By.xpath("//button[contains(text(),' Update ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    private By incomeHead=By.xpath("//span[text()='Income Head']");

    private String updateServiceCategory;

    // ===== Constructor =====
    public EditServiceCategoriesPage(WebDriver driver) {

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

    // ===== Actions =====

    public void clickRow() {

        waitForAngularIdle();

        WebElement row =
                wait.until(ExpectedConditions.elementToBeClickable(rowclick));

        jsClick(row);
    }

    public void editServiceCategories() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        waitForAngularIdle();

        wait.until(ExpectedConditions.visibilityOfElementLocated(serviceLine))
                .sendKeys("Name " + randomName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(desc))
                .sendKeys("Desc " + randomName);

        

        waitForAngularIdle();

        WebElement update =
                wait.until(ExpectedConditions.visibilityOfElementLocated(updateButton));

        jsClick(update);
    }

    public void resetServiceCategories() {

        String randomName =
                RandomStringUtils.randomAlphabetic(4);

        waitForAngularIdle();

        wait.until(ExpectedConditions.visibilityOfElementLocated(serviceLine))
                .sendKeys("Name " + randomName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(desc))
                .sendKeys("Desc " + randomName);

        waitForAngularIdle();

        WebElement reset =
                wait.until(ExpectedConditions.visibilityOfElementLocated(resetButton));

        jsClick(reset);
    }

    public String getUpdateServiceCategory() {

        return updateServiceCategory;
    }

    public String getToastMsg() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        for (int i = 0; i < 15; i++) {

            String toast = (String) js.executeScript(
                    "var e=document.querySelector('#toast-popup p'); return e?e.innerText:'';"
            );

            if (!toast.isEmpty()) {
                return toast.trim();
            }

            try {
                Thread.sleep(200);
            } catch (Exception e) {}
        }

        return "";
    }

    // ===== Reset Validation Getters =====

    public String getServiceNameValue() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(serviceLine))
                .getAttribute("value")
                .trim();
    }

    public String getServiceDescriptionValue() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(desc))
                .getAttribute("value")
                .trim();
    }

    public String getServiceHSNCodeValue() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(hsnCode))
                .getAttribute("value")
                .trim();
    }
}