package com.idsnext.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportButtonPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ===== LOCATORS =====

    private By resetButton =
            By.xpath("//span[text()='Reset'] | //button[contains(text(),'Reset')]");

    private By backButton =
            By.xpath("//button[contains(text(),'Back')]");

    private By emailButton = 
            By.xpath("//button[contains(text(),'Email')]");

    private By printButton = 
            By.xpath("//button[contains(text(),'Print')]");

    private By exportPdfButton =
            By.xpath("//button[contains(text(),'Export to PDF')] | //button[contains(text(),'Export to Pdf')]");

    private By exportExcelButton =
            By.xpath("//button[contains(text(),'Export to Excel')]");

    private By reportRows =
            By.xpath("//table/tbody/tr");
    private By cancel =
            By.xpath("//button[text()='Cancel']");


    // ===== CONSTRUCTOR =====

    public ReportButtonPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }


    // ===== ACTIONS =====

    public void clickReset() {
        waitForAngularIdle();
        WebElement reset = wait.until(ExpectedConditions.elementToBeClickable(resetButton));
        jsClick(reset);
        waitForAngularIdle();
    }

    public void clickBack() {
        WebElement back = wait.until(ExpectedConditions.elementToBeClickable(backButton));
        jsClick(back);
        waitForAngularIdle();
    }

    public void clickEmail() {
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(emailButton));
        jsClick(email);
        waitForAngularIdle();
        click(cancel);
    }

    public void clickPrint() {
        WebElement print = wait.until(ExpectedConditions.elementToBeClickable(printButton));
        jsClick(print);
        waitForAngularIdle();
    }

  public void clickExportPdf() {
    // 1. Wait for the button and click it
    WebElement pdf = wait.until(ExpectedConditions.elementToBeClickable(exportPdfButton));
    jsClick(pdf);
    waitForAngularIdle();

    // 2. Handle the native Windows "Save As" dialog box immediately
    try {
        Robot robot = new Robot();
        
        // Give the window a brief split-second to appear on screen
        robot.delay(1000); 
        
        // Press ENTER to accept the file name and click the "Save" button
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        System.out.println("Native Save As dialog handled successfully via Robot Enter key.");
        waitForAngularIdle();
    } catch (Exception e) {
        System.out.println("Robot failed to press Enter on the native window: " + e.getMessage());
    }
}

public void clickExportExcel() {
    // 1. Wait until the Excel button is fully present and clickable
    WebElement excel = wait.until(ExpectedConditions.elementToBeClickable(exportExcelButton));
    
    // 2. Trigger the click using JavaScript
    jsClick(excel);
    waitForAngularIdle();
    
    // 3. Handle the native Windows "Save As" dialog box immediately
    try {
        Robot robot = new Robot();
        
        // Give the window a brief split-second to appear on screen
        robot.delay(1000); 
        
        // Press ENTER to accept the file name and click the "Save" button
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        System.out.println("Native Save As dialog for Excel handled successfully via Robot.");
        waitForAngularIdle();
    } catch (Exception e) {
        System.out.println("Robot failed to press Enter on the native Excel window: " + e.getMessage());
    }
}


    // ===== VALIDATIONS =====

    public boolean isReportVisible() {
    return driver.findElements(reportRows).isEmpty(); 
}
public boolean isButtonReset() {
    try {
        // 1. Locate the date input element directly using the ID from your snippet
        By fromDateLocator = By.id("mat-input-0");
        WebElement dateInput = driver.findElement(fromDateLocator);
        
        // 2. Get the actual text currently typed inside the input box
        String actualDate = dateInput.getAttribute("value");
        
        // 3. Get today's system date formatted to match your UI layout (e.g., dd/MM/yyyy)
        // Adjust the pattern to "MM/dd/yyyy" if your UI displays month first!
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        System.out.println("Actual Date in UI: " + actualDate);
        System.out.println("Expected Today's Date: " + todayDate);
        
        // 4. Return true if the reset value matches today's date
        return actualDate.trim().equals(todayDate);
        
    } catch (Exception e) {
        System.out.println("Failed to read the calendar reset state: " + e.getMessage());
        return false;
    }
}

    public boolean isPdfDownloaded(String downloadPath) {
        File folder = new File(downloadPath);
        File[] files = folder.listFiles();
        if (files == null) return false;
        
        for (File file : files) {
            if (file.getName().endsWith(".pdf")) {
                return true;
            }
        }
        return false;
    }

    public boolean isExcelDownloaded(String downloadPath) {
        File folder = new File(downloadPath);
        File[] files = folder.listFiles();
        if (files == null) return false;

        for (File file : files) {
            if (file.getName().endsWith(".xlsx")) {
                return true;
            }
        }
        return false;
    }
}