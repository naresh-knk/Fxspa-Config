package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditBudgetDetailsPage extends BasePage {

    // Locators
    private By rowclick =
            By.xpath("(//td[contains(text(),'FX')])[1]");

    private By financialYear =
            By.xpath("//input[@placeholder='Financial Year']");

    private By april =
            By.xpath("(//input[@matinput])[3]");

    private By may =
            By.xpath("(//input[@matinput])[4]");

    private By june =
            By.xpath("(//input[@matinput])[5]");

    private By july =
            By.xpath("(//input[@matinput])[6]");

    private By august =
            By.xpath("(//input[@matinput])[7]");

    private By sept =
            By.xpath("(//input[@matinput])[8]");

    private By oct =
            By.xpath("(//input[@matinput])[9]");

    private By nov =
            By.xpath("(//input[@matinput])[10]");

    private By dec =
            By.xpath("(//input[@matinput])[11]");

    private By jan =
            By.xpath("(//input[@matinput])[12]");

    private By feb =
            By.xpath("(//input[@matinput])[13]");

    private By march =
            By.xpath("(//input[@matinput])[14]");

    private By updateButton =
            By.xpath("//button[contains(text(),' Update ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    // Constructor
    public EditBudgetDetailsPage(WebDriver driver) {
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

    // Click Existing Row
    public void clickRow() {

        WebElement row =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                rowclick
                        )
                );

        jsClick(row);
    }

    // Update Budget Details
    public void updateBudgetDetails() {

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        april
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        may
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        june
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        july
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        august
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        sept
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        oct
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        nov
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        dec
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        jan
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        feb
                )
        ).sendKeys(randomNumeric);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        march
                )
        ).sendKeys(randomNumeric);

        WebElement update =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                updateButton
                        )
                );

        jsClick(update);
    }

    // Reset Budget Details
    public void resetBudgetDetails() {

        WebElement reset =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                resetButton
                        )
                );

        jsClick(reset);
    }

    // Toast Message
    public String getToastMsg() {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        for (int i = 0; i < 15; i++) {

            String toast =
                    (String) js.executeScript(
                            "var e=document.querySelector('#toast-popup p');"
                                    + " return e?e.innerText:'';"
                    );

            if (!toast.isEmpty()) {
                return toast.trim();
            }

            try {
                Thread.sleep(200);
            } catch (Exception ignored) {
            }
        }

        return "";
    }

    // Reset Validation Getter
    public String getBudgetDetailsNameValue() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        financialYear
                )
        ).getAttribute("value").trim();
    }
}