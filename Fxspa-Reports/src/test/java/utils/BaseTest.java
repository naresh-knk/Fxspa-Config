// package utils;

// import java.lang.reflect.Method;
// import java.time.Duration;
// import java.util.Collections;
// import java.util.HashSet;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Set;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.testng.ITestResult;
// import org.testng.annotations.AfterMethod;
// import org.testng.annotations.AfterSuite;
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.BeforeSuite;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Listeners;
// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.ExtentTest;
// import com.idsnext.pages.AppointmentReportPage;
// import com.idsnext.pages.LoginPage;

// import io.github.bonigarcia.wdm.WebDriverManager;

// @Listeners(utils.AiReportListener.class)
// public class BaseTest {

//     protected static WebDriver driver;
//     protected static ExtentReports extent;
//     protected ExtentTest test;
//     protected ConfigReader config = new ConfigReader();

//     public static Set<String> failedTestNames = Collections.synchronizedSet(new HashSet<>());

//     public static WebDriver getDriver() {
//         return driver;
//     }

//     @BeforeSuite(alwaysRun = true)
//     public void globalSetup() {
//         extent = ExtentManager.getInstance();
        
//         if (driver == null) {
//             WebDriverManager.chromedriver().setup();

//             Map<String, Object> chromePrefs = new HashMap<>();
//             chromePrefs.put("credentials_enable_service", false);
//             chromePrefs.put("profile.password_manager_enabled", false);
//             chromePrefs.put("autofill.profile_enabled", false);

//             ChromeOptions options = new ChromeOptions();
//             options.setExperimentalOption("prefs", chromePrefs);
//             options.addArguments("--incognito", "--disable-save-password-bubble", "--no-sandbox", "--disable-dev-shm-usage");

//             driver = new ChromeDriver(options);
//             driver.manage().window().maximize();
//             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

//             // Step 1: Login
//             driver.get(config.getUrl());
//             LoginPage loginPage = new LoginPage(driver);
//             loginPage.login(config.getUsername(), config.getPassword());
            
//             // Step 2: Click FX Report to establish primary dashboard landing
//             AppointmentReportPage reportPage = new AppointmentReportPage(driver);
//             reportPage.clickFXReport(); 
//         }
//     }

//     // ===== NEW: BEFORE CLASS HANDLES RUNNING ONCE PER CLASS ENVIRONMENT =====
//     @BeforeClass(alwaysRun = true)
//     public void setupClassContext() {
//         if (driver != null) {
//             String className = this.getClass().getSimpleName();
            
//             // Special Handle for LoginTest: Reset session if verifying the actual Login page
//             if (className.equals("LoginTest")) {
//                 driver.manage().deleteAllCookies();
//                 driver.get(config.getUrl());
//                 return; 
//             }

//             AppointmentReportPage reportPage = new AppointmentReportPage(driver);
//             try {
//                 // Force a fresh URL load of the portal dashboard to break out of active sub-modules
//                 driver.get(config.getUrl()); 
//                 reportPage.clickFXReport();
//             } catch (Exception e) {
//                 System.out.println("UI view state structurally blocked. Triggering structural DOM refresh...");
//                 driver.navigate().refresh();
//                 reportPage.clickFXReport();
//             }
//         }
//     }

//     @BeforeMethod(alwaysRun = true)
//     public void prepareTestContext(Method method) {
//         test = extent.createTest(method.getName());
//         ExtentTestManager.setTest(test);
        
//         // Custom URL refreshes have been completely extracted into setupClassContext() 
//         // to maintain continuous state between consecutive method priorities.
//     }

//     @AfterMethod(alwaysRun = true)
//     public void tearDown(ITestResult result) {
//         if (result.getStatus() == ITestResult.FAILURE) {
//             failedTestNames.add(result.getMethod().getMethodName());
//         }
        
//         // Re-authenticate session smoothly if the dedicated LoginTest just ran
//         if (result.getMethod().getRealClass().getSimpleName().equals("LoginTest")) {
//             driver.get(config.getUrl());
//             LoginPage loginPage = new LoginPage(driver);
//             loginPage.login(config.getUsername(), config.getPassword());
//             AppointmentReportPage reportPage = new AppointmentReportPage(driver);
//             reportPage.clickFXReport();
//         }
//     }

//     @AfterSuite(alwaysRun = true)
//     public void flushExtentReport() {
//         try {
//             if (driver != null) {
//                 driver.quit();
//                 driver = null;
//             }
//         } finally {
//             extent.flush();
//         }
//     }
// }

package utils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.idsnext.pages.AppointmentReportPage;
import com.idsnext.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(utils.AiReportListener.class)
public class BaseTest {

    protected static WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected ConfigReader config = new ConfigReader();

    public static Set<String> failedTestNames = Collections.synchronizedSet(new HashSet<>());

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        extent = ExtentManager.getInstance();
        
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("autofill.profile_enabled", false);
            
            // ===== AUTOMATIC FILE DOWNLOAD CONFIGURATION =====
            chromePrefs.put("download.default_directory", "C:\\Users\\Deepshika\\Downloads");
            chromePrefs.put("download.prompt_for_download", false);
            chromePrefs.put("plugins.always_open_pdf_externally", true); 

            // ===== FIX: ALLOW MULTIPLE FILE DOWNLOADS AUTOMATICALLY =====
            // 1 = Allow, 2 = Block. This completely suppresses the security popup shown in your image.
            chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1); 

            // ===== ADDITIONAL HANDLING FOR PRINT-TO-PDF ROUTINES =====
            chromePrefs.put("savefile.default_directory", "C:\\Users\\Deepshika\\Downloads");
            String printAppState = "{\"recentDestinations\":[{\"id\":\"Save as PDF\",\"origin\":\"local\",\"account\":\"\"}],\"selectedDestinationId\":\"Save as PDF\",\"version\":2}";
            chromePrefs.put("printing.print_preview_sticky_settings.appState", printAppState);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            
            // ===== BROWSER ARGUMENTS =====
            options.addArguments(
                "--incognito", 
                "--disable-save-password-bubble", 
                "--no-sandbox", 
                "--disable-dev-shm-usage",
                "--kiosk-printing" 
            );

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Step 1: Login
            driver.get(config.getUrl());
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(config.getUsername(), config.getPassword());
            
            // Step 2: Click FX Report to establish primary dashboard landing
            AppointmentReportPage reportPage = new AppointmentReportPage(driver);
            reportPage.clickFXReport(); 
        }
    }

    // ===== BEFORE CLASS HANDLES RUNNING ONCE PER CLASS ENVIRONMENT =====
    @BeforeClass(alwaysRun = true)
    public void setupClassContext() {
        if (driver != null) {
            String className = this.getClass().getSimpleName();
            
            if (className.equals("LoginTest")) {
                driver.manage().deleteAllCookies();
                driver.get(config.getUrl());
                return; 
            }

            AppointmentReportPage reportPage = new AppointmentReportPage(driver);
            try {
                driver.get(config.getUrl()); 
                reportPage.clickFXReport();
            } catch (Exception e) {
                System.out.println("UI view state structurally blocked. Triggering structural DOM refresh...");
                driver.navigate().refresh();
                reportPage.clickFXReport();
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void prepareTestContext(Method method) {
        test = extent.createTest(method.getName());
        ExtentTestManager.setTest(test);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            failedTestNames.add(result.getMethod().getMethodName());
        }
        
        if (result.getMethod().getRealClass().getSimpleName().equals("LoginTest")) {
            driver.get(config.getUrl());
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(config.getUsername(), config.getPassword());
            AppointmentReportPage reportPage = new AppointmentReportPage(driver);
            reportPage.clickFXReport();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void flushExtentReport() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } finally {
            extent.flush();
        }
    }
}