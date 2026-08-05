package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.idsnext.enums.ModuleName;

public class NavigationPage extends BasePage {

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    // Common Locator
    private By randomIcon =
            By.xpath("//i[contains(@class,'fa-random')]");

    private By expandedMenu =
            By.cssSelector("span.menu-span.rotate");

    // Module Locators
    private By services =
            By.xpath("//span[normalize-space()='Services']");

    private By resources =
            By.xpath("//span[normalize-space()='Resources']");

    private By product =
            By.xpath("//span[normalize-space()='Products']");

    private By staffMapping =
            By.xpath("//span[normalize-space()='Staff Mapping']");

    private By serviceCategory =
            By.xpath("//span[normalize-space()='Service Categories']");

    private By productCategory =
            By.xpath("//span[normalize-space()='Product Categories']");

    private By packages =
            By.xpath("//span[text()=' Packages ']");

    private By budgetDetails =
            By.xpath("//span[normalize-space()='Budget Details']");

    private By advancedSettings =
            By.xpath("//span[normalize-space()='Advanced Settings']");

    private By billSeries =
            By.xpath("//span[text()=' Bill Series Setup ']");

    private By plusButton =
            By.xpath("//button[normalize-space()='+']");


    public void clickAdd() {

        waitForAngularIdle();

        WebElement plus =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                plusButton));

        jsClick(plus);
    }

    public void clickRandom() {

        waitForAngularIdle();

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                randomIcon));

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);

        waitForAngularIdle();
    }

    private void ensureMenuExpanded() {

        try {

            if (!driver.findElement(expandedMenu).isDisplayed()) {

                clickRandom();
            }

        } catch (Exception e) {

            clickRandom();
        }
    }

    private void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // Dynamic Module Navigation
    public void clickModule(ModuleName moduleName) {

        ensureMenuExpanded();

        switch (moduleName) {

            case SERVICES:
                click(services);
                break;

            case RESOURCES:
                click(resources);
                break;

            case PRODUCTS:
                click(product);
                break;

            case STAFF_MAPPING:
                click(staffMapping);
                break;

            case SERVICE_CATEGORY:
                click(serviceCategory);
                break;

            case PRODUCT_CATEGORY:
                click(productCategory);
                break;

            case PACKAGES:
                click(packages);
                break;

            case BUDGET_DETAILS:
                click(budgetDetails);
                break;

            case ADVANCED_SETTINGS:
                click(advancedSettings);
                break;

            case BILL_SERIES:
                click(billSeries);
                break;

            default:
                throw new RuntimeException(
                        "Module not found: " + moduleName);
        }
    }
}