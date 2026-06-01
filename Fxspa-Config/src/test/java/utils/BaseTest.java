package utils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.idsnext.pages.LoginPage;
import com.idsnext.pages.ServicesPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected static WebDriver driver;

    protected static ExtentReports extent;
    protected ExtentTest test;

    protected ConfigReader config = new ConfigReader();

    // ===== EXTENT REPORT =====
    @BeforeSuite
    public void startReport() {

        extent = ExtentManager.getInstance();
    }

    // ===== OPEN BROWSER + LOGIN ONLY ONCE =====
    @BeforeClass(alwaysRun = true)
    public void setupBrowser() {

        // Prevent reopening browser
        if (driver == null) {

            WebDriverManager.chromedriver().setup();

            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("autofill.profile_enabled", false);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            options.addArguments("--headless=new");
            options.addArguments("--incognito");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);

            driver.manage().window().maximize();

            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(5));

            // ===== OPEN URL =====
            driver.get(config.getUrl());

            // ===== LOGIN =====
            LoginPage loginPage = new LoginPage(driver);

            loginPage.login(
                    config.getUsername(),
                    config.getPassword()
            );

            // ===== OPEN FX SPA CONFIG =====
            ServicesPage servicesPage = new ServicesPage(driver);

            servicesPage.clickFXSPAConfigIcon();

            servicesPage.switchWindow();
        }
    }

    // ===== CREATE TEST IN REPORT =====
    @BeforeMethod
    public void createTest(Method method) {

        test = extent.createTest(method.getName());

        ExtentTestManager.setTest(test);
    }

    // ===== REPORT HANDLING =====
    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            String screenshotPath =
                    ScreenshotUtils.captureScreenshot(driver, result.getName());

            ExtentTestManager.getTest()
                    .fail(result.getThrowable())
                    .addScreenCaptureFromPath(
                            screenshotPath,
                            "Failure Screenshot"
                    );
        }

        if (result.getStatus() == ITestResult.SUCCESS) {

            ExtentTestManager.getTest().pass("Test Passed");
        }

        if (result.getStatus() == ITestResult.SKIP) {

            ExtentTestManager.getTest().skip("Test Skipped");
        }
    }

    // ===== CLOSE BROWSER ONLY ONCE =====
    @AfterSuite
    public void closeBrowser() {

        extent.flush();

        if (driver != null) {

            driver.quit();

            driver = null;
        }
    }
}