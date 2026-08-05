package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    // Locators
    private By username = By.xpath("//input[@type='text']");
    private By password = By.xpath("//input[@type='password']");
    private By loginButton = By.xpath("//button[.//span[normalize-space()='Login']]");
    private By randomIcon = By.xpath("/html/body/app-root/div[1]/nav/ul/li/a/span[1]");

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
        return isElementVisible(randomIcon); // Already using BasePage method
    }
}
