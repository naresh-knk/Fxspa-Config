package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppointmentCalendarPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By fxOperationIcon =
            By.xpath("//div[contains(@title,'FX SPA (FX SPA)')]");

    private By randomIcon =
            By.xpath("//i[contains(@class,'fa fa-random')]");

    private By AppointmentCalendar =
            By.xpath("//span[normalize-space()='Appointment Calendar']");

    private By loader =
            By.xpath("//div[contains(@class,'loader-outer')]");

    private By overlayBackdrop =
            By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]");

    private By reportTable =
            By.xpath("(//tr[1])[1]");

    private By nextButton= By.xpath("//button[@mattooltip='Next']");

     private By outlets= By.xpath("(//div[contains(@class,'mat-mdc-select-arrow-wrapper')])[3]");

     private By newCalendar = By.xpath("//a[text()='New Calendar(Beta)']");


    private By availableSlots =
        By.xpath("//mat-cell[not(.//div[contains(@class,'sub-container')])]//div[contains(@class,'sub-time')]");

    private By search=By.xpath("//button[contains(@class,'search-field-btn')]");
    private By selectOption= By.xpath("(//img[@class='guest-icon'])[1]");
    private By addService=By.xpath("//a[text()=' Add Service']");
    private By selectService=By.xpath("//app-service-panel//mat-accordion//div[@class='ser-content']//mat-expansion-panel[1]");
    private By selectSubService=By.xpath("//app-service-panel//mat-expansion-panel[1]//div[contains(@class,'services ng-star-inserted')][1]");
    private By resources=By.xpath("(//span[contains(@class,'placeholder')])[1]");
    private By saveAppointment=By.xpath("//button[text()=' Save Appointment ']");
    private String CreatedAppointment;
    private By addNewService=By.xpath("//a[text()=' Add New Services']");
    private By addGuest=By.xpath("//span[text()='Add Guest']");
    private By buttonSearch=By.xpath("//button[text()='Search']");
    private By searchRow=By.xpath("//tbody[@class='ng-star-inserted'][1]");


    // ===== Constructor =====

    public AppointmentCalendarPage(WebDriver driver) {

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

//     

public void clickRandomAvailableSlot() {

    waitForAngularIdle();

    List<WebElement> slots =
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//mat-cell[not(.//div[contains(@class,'sub-container')])]//div[contains(@class,'sub-time')]")
            ));

    if (slots.isEmpty()) {
        throw new NoSuchElementException("No available slots found");
    }

    // pick random slot
    Random random = new Random();
    int randomIndex = random.nextInt(slots.size());

    WebElement selectedSlot = slots.get(randomIndex);

    // scroll to slot
    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView({block:'center'});", selectedSlot);

    // wait until clickable
    wait.until(ExpectedConditions.elementToBeClickable(selectedSlot));

    // click using JS
    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", selectedSlot);

    System.out.println("Clicked available slot index: " + randomIndex);

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

    public void clickAppointmentCalendar() {

        waitForAngularIdle();

        WebElement element =
                wait.until(ExpectedConditions.presenceOfElementLocated(AppointmentCalendar));

        jsClick(element);

    }

public String getCreatedAppointment() {
    return CreatedAppointment.trim();
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
        } catch (Exception e) {}
    }

    return "";
}

    public void nextButton() {

    waitForAngularIdle();

    WebElement next =
            wait.until(ExpectedConditions.elementToBeClickable(nextButton));

    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", next);

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

    public void createNewAppointment(){
     waitForAngularIdle();
    wait.until(ExpectedConditions.elementToBeClickable(search)).click();
    wait.until(ExpectedConditions.elementToBeClickable(selectOption)).click();
    wait.until(ExpectedConditions.elementToBeClickable(addService)).click();
    wait.until(ExpectedConditions.elementToBeClickable(selectService)).click();
    wait.until(ExpectedConditions.elementToBeClickable(selectSubService)).click();
    selectFromMatDropdownByIndex(resources, 0);
    Actions actions = new Actions(driver); // click somewhere outside 
    actions.moveByOffset(10, 10).click().perform();
     waitForAngularIdle();
    }

    public void addGuest(){
         wait.until(ExpectedConditions.elementToBeClickable(addGuest)).click();
        //  wait.until(ExpectedConditions.elementToBeClickable(search)).click();
        //  wait.until(ExpectedConditions.elementToBeClickable(buttonSearch)).click();
         wait.until(ExpectedConditions.elementToBeClickable(searchRow)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addService)).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectService)).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectSubService)).click();
        selectFromMatDropdownByIndex(resources, 0);
        Actions actions = new Actions(driver); // click somewhere outside 
        actions.moveByOffset(10, 10).click().perform();
        waitForAngularIdle();
    }

     public void saveAppointment() {
        waitForAngularIdle();
        wait.until(ExpectedConditions.elementToBeClickable(saveAppointment)).click();    
    }

   
    public void clickOutlets() {
        waitForAngularIdle();
       selectFromMatDropdownByIndex(outlets, 0);
    }

     
public void clickNewCalendar() {

    waitForAngularIdle();

    WebElement calendar =
            wait.until(ExpectedConditions.elementToBeClickable(newCalendar));

    jsClick(calendar);

    waitForAngularIdle();
}     

}