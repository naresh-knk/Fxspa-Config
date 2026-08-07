package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // ===== Common Loaders =====

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By loader =
            By.cssSelector(".loader-outer");

    private By outlets= By.xpath("//mat-label[text()='Outlets']");

    // ===== Constructor =====

    public BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
    }

  

    protected void waitForAngularIdle() {

        try {

            WebDriverWait smallWait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            smallWait.until(driver -> {

                List<WebElement> loaders =
                        driver.findElements(loader);

                for (WebElement element : loaders) {

                    try {

                        if (element.isDisplayed()) {
                            return false;
                        }

                    } catch (StaleElementReferenceException ignored) {
                    }
                }

                return true;
            });

        } catch (Exception ignored) {
        }

        // ===== Wait Overlay =====

        try {

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            overlayBackdrop));

        } catch (Exception ignored) {
        }
    }

    public void selectFromMatDropdownByText(By dropdown, String text) {
    click(dropdown);

    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//mat-option")));

    for (WebElement option : options) {
        if (option.getText().trim().equalsIgnoreCase(text)) {
            jsClick(option);
            return;
        }
    }

    throw new NoSuchElementException("Option not found: " + text);
}

     public void clickOutlets() {
    waitForAngularIdle();

    try {
        selectFromMatDropdownByText(outlets, "Spa Plein De Vie"); 
    } catch (Exception e) {
        selectFromMatDropdownByIndex(outlets, 0);
    }
    Actions action = new Actions(driver);
    action.sendKeys(Keys.ESCAPE).perform();
}

    public void selectFromMatDropdownByIndex(By dropdown, int index) {

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


    public boolean isElementVisible(By locator) {

        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            locator));

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }


   public void click(By locator) {
    waitForAngularIdle();

    // 1. Wait for interactability
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    scrollIntoView(element);

    // 2. Fix for 2nd click: If it's an input box with existing text, clear it out!
    try {
        String tagName = element.getTagName();
        if (tagName.equalsIgnoreCase("input") && !element.getAttribute("value").isEmpty()) {
            element.clear(); 
            // Alternative deep clear if Angular blocks .clear()
            element.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"), org.openqa.selenium.Keys.BACK_SPACE);
        }
    } catch (Exception e) {
        // Not an input field or clear failed, safe to skip
    }

    // 3. Perform the click action
    try {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    } catch (Exception e) {
        System.out.println("Standard click failed due to: " + e.getMessage() + ". Attempting JS Click fallback.");
        WebElement freshElement = driver.findElement(locator);
        jsClick(freshElement);
    }

    waitForAngularIdle();
}
 

    public void type(By locator, String text) {

        waitForAngularIdle();

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator));

        scrollIntoView(element);

        element.clear();

        element.sendKeys(text);
    }


    public String getText(By locator) {

        waitForAngularIdle();

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        locator))
                .getText()
                .trim();
    }



    protected void jsClick(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        element);
    }


    protected void scrollIntoView(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element);
    }

   

    public WebElement waitUntilClickable(By locator) {

        waitForAngularIdle();

        return wait.until(
                ExpectedConditions.elementToBeClickable(
                        locator));
    }


    public WebElement waitUntilVisible(By locator) {

        waitForAngularIdle();

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        locator));
    }
}
