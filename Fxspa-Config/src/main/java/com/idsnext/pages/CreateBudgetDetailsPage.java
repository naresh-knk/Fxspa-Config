package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateBudgetDetailsPage extends BasePage {

    // Locators
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

    private By createButton =
            By.xpath("//button[contains(text(),' Create ')]");

    private By resetButton =
            By.xpath("//button[contains(text(),'Reset')]");

    // Constructor
    public CreateBudgetDetailsPage(WebDriver driver) {
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

    // Create Budget Details
    public void createBudgetDetails() {

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        financialYear
                )
        ).sendKeys("2022-2023");

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

        WebElement create =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                createButton
                        )
                );

        jsClick(create);
    }

    // Reset Budget Details
    public void resetBudgetDetails() {

        String randomNumeric =
                RandomStringUtils.randomNumeric(2);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        financialYear
                )
        ).sendKeys("2023-2024");

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

        By toastMessage =
                By.xpath("//div[@id='toast-popup']//p");

        try {

            WebElement toast =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    toastMessage
                            )
                    );

            return toast.getText().trim();

        } catch (Exception e) {

            return "";
        }
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