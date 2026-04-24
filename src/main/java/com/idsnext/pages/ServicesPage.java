package com.idsnext.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ServicesPage extends BasePage {

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

    private By services=
            By.xpath("//span[normalize-space()='Services']");

            private By plusButton = By.xpath("//button[normalize-space()='+']");

    private By sname= By.xpath("//input[@placeholder='Service Name']");
    private By sdesc= By.xpath("//input[contains(@placeholder,'Service Description')]");
    private By sduration= By.xpath("//input[contains(@placeholder,'Duration(Minutes)')]");
    private By selectServiceCategory = By.xpath("//span[text()='Select Service Category']");
    private By serviceAvailableFor=By.xpath("//span[text()='Service Available For']");
    private By ranclick= By.xpath("(//mat-option[@role='option'])[1]");
    private By resource=By.xpath("//span[text()='Resource']");
    private By property=By.xpath("//span[text()='Property']");
    private By spaOutlets= By.xpath("//span[text()='Spa Outlets']");
        private By checkBox=By.xpath("//mat-label[text()='Week Days']/following-sibling::*");

    private By sAmt= By.xpath("(//input[contains(@placeholder,'Amount')])[1]");
    private By sPrice= By.xpath("//input[contains(@formcontrolname,'SpecialAmount')]");
    private String createdServiceName;  

    
    private By createButton =
            By.xpath("//button[contains(text(),' Save ')]");


    // ===== Constructor =====
    public ServicesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    // ===== Common Waits =====

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


    
    private void selectFromMatDropdown(By dropdown, String optionText) {
        waitForAngularIdle();

        WebElement dd =
                wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        jsClick(dd);

        By option =
                By.xpath("//mat-option//span[normalize-space()='" + optionText + "']");
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

    public void switchWindow(){
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

     public void clickRandom() {

    // Wait for Angular API calls to finish
    waitForAngularIdle();

    // Wait for loader to disappear
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader-outer")));

    // Wait for element to be clickable
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(randomIcon));

    // Scroll to element (helps if element is not visible)
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    // Click element
    element.click();
}


    public void clickServices() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(services)).click();
        waitForAngularIdle();
    }

     public void clickAdd() {
        waitForAngularIdle();

        WebElement plus =
                wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        jsClick(plus);
    }

 
    public void createServices() {

    String randomName = RandomStringUtils.randomAlphabetic(4);
    String randomNumeric = RandomStringUtils.randomNumeric(2);

    createdServiceName = "Name " + randomName;   // store generated service name

    waitForAngularIdle();

    wait.until(ExpectedConditions.visibilityOfElementLocated(sname))
            .sendKeys(createdServiceName);

    wait.until(ExpectedConditions.visibilityOfElementLocated(sdesc))
            .sendKeys("Desc " + randomName);

    wait.until(ExpectedConditions.visibilityOfElementLocated(sduration))
            .sendKeys(randomNumeric);

    selectFromMatDropdown(selectServiceCategory, "Ayurveda");
    selectFromMatDropdown(serviceAvailableFor, "All");

    driver.findElement(ranclick).sendKeys(Keys.ESCAPE);

    selectFromMatDropdown(resource, "resources11");

    driver.findElement(ranclick).sendKeys(Keys.ESCAPE);
    selectFromMatDropdown(property, "FX FD Central QA Property1 - 900003 (900003)");
    selectFromMatDropdown(spaOutlets, "Spa Plein De Vie");

    driver.findElement(ranclick).sendKeys(Keys.ESCAPE);

    wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox)).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(sAmt))
            .sendKeys(randomNumeric);

    wait.until(ExpectedConditions.visibilityOfElementLocated(sPrice))
            .sendKeys(randomNumeric);

    waitForAngularIdle();

    WebElement create =
            wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
    jsClick(create);

    waitForAngularIdle();
}

public String getCreatedServiceName() {
    return createdServiceName;
}

public String getFirstRowServiceName() {

    By firstRowService = By.xpath("//*[@id=\"maintable\"]/table/tbody/tr[2]/td[2]");

    return wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowService))
            .getText()
            .trim();
}

    

}