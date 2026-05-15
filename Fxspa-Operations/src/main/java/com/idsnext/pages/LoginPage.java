package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    // Locators
    private By username = By.id("md-input-0-input");
    private By password = By.id("md-input-1-input");
    private By loginButton = By.xpath("//button[.//span[normalize-space()='Login']]");
    private By fxPosImage = By.xpath("//img[@alt='FX POS']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ---------------- Login method using BasePage helper methods ----------------
    public void login(String user, String pass) {
        type(username, user);   // Updated to use BasePage.type()
        type(password, pass);
        click(loginButton);     // Updated to use BasePage.click()
    }

    // ---------------- Check if login is successful ----------------
    public boolean isLoginSuccessful() {
        return isElementVisible(fxPosImage); // Already using BasePage method
    }
}
