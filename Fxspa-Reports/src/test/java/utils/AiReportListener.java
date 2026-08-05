package utils;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.json.JSONObject;
import java.io.File;

public class AiReportListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        try {
            // Adds the global environment metadata features to the Extent Report UI dashboard
            ExtentManager.getInstance().setSystemInfo("Execution Environment", "QA-Automation");
            ExtentManager.getInstance().setSystemInfo("Execution Machine", System.getProperty("user.name"));
            ExtentManager.getInstance().setSystemInfo("OS Architecture", System.getProperty("os.name"));
            ExtentManager.getInstance().setSystemInfo("AI Core Engine", "Gemini-2.5-Flash (prescriptive)");
        } catch (Exception e) {
            System.out.println("[AI LISTENER] Global metadata setup failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().assignAuthor("AI Cognitive Agent");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        
        // Safety lock: explicitly log this test name as failed immediately to sync browser context
        BaseTest.failedTestNames.add(testName);

        String exceptionMsg = (result.getThrowable() != null) ? result.getThrowable().getMessage() : "No trace message captured.";
        
        StringBuilder stackTraceBuilder = new StringBuilder();
        if (result.getThrowable() != null) {
            for (StackTraceElement element : result.getThrowable().getStackTrace()) {
                stackTraceBuilder.append(element.toString()).append("\n");
            }
        }

        String relativeImgPath = "";
        File screenshotFile = null;
        try {
            WebDriver activeDriver = BaseTest.getDriver();
            if (activeDriver != null) {
                relativeImgPath = ScreenshotUtils.captureScreenshot(activeDriver, testName);
                screenshotFile = new File("test-output/" + relativeImgPath);
            }
        } catch (Exception e) {
            System.out.println("[AI LISTENER] Screenshot capture failed: " + e.getMessage());
        }

        // Fetch response from AI Engine
        String aiRaw = GeminiReportAnalyzer.analyzeFailure(testName, exceptionMsg, stackTraceBuilder.toString(), screenshotFile);

        String category = "Execution Error";
        String confidence = "70";
        String diagnosis = "Unable to process log data.";
        String fix = "Inspect console output manually.";

        // Safe JSON parsing
        try {
            JSONObject json = new JSONObject(aiRaw.trim());
            category = json.optString("category", "Execution Error");
            confidence = String.valueOf(json.optInt("confidence", 70));
            diagnosis = json.optString("diagnosis", "No clear diagnosis generated.");
            fix = json.optString("fix", "No fix recommended.");
        } catch (Exception e) {
            diagnosis = aiRaw; // fallback if plain text string returned
        }

        try {
            if (ExtentTestManager.getTest() != null) {
                // Hard force failure status inside the report engine core model to keep test count synchronized
                ExtentTestManager.getTest().getModel().setStatus(Status.FAIL);
                ExtentTestManager.getTest().fail(result.getThrowable());
                
                if (!relativeImgPath.isEmpty()) {
                    ExtentTestManager.getTest().addScreenCaptureFromPath(relativeImgPath, "Failure Screenshot");
                }
                
                ExtentTestManager.getTest().assignCategory(category);
                ExtentTestManager.getTest().assignAuthor("AI Cognitive Agent");

                // Inject Custom UI Component Block for Gemini Insight
                String advancedDashboardHtml = 
                    "<div style='border: 1px solid #e0e0e0; border-radius: 8px; font-family: Segoe UI, Tahoma, sans-serif; background: #ffffff; box-shadow: 0 4px 6px rgba(0,0,0,0.05); margin: 15px 0; overflow: hidden;'>" +
                    "  <div style='background: #343a40; padding: 12px 15px; color: #ffffff; font-weight: bold; font-size: 14px; display: flex; align-items: center; justify-content: space-between;'>" +
                    "    <span>🤖 INTUITIVE COGNITIVE DIAGNOSIS</span>" +
                    "    <span style='background: #dc3545; padding: 3px 8px; border-radius: 12px; font-size: 11px; text-transform: uppercase; letter-spacing: 0.5px;'>Live Metrics Layer</span>" +
                    "  </div>" +
                    "  <div style='padding: 15px;'>" +
                    "    <table style='width: 100%; border-collapse: collapse; margin-bottom: 15px; text-align: left;'>" +
                    "      <tr>" +
                    "        <td style='padding: 5px 0; font-size: 13px; color: #6c757d; width: 30%;'>Failure Tag:</td>" +
                    "        <td style='padding: 5px 0; font-size: 13px; font-weight: 600; color: #212529;'><span style='background:#e9ecef; padding:2px 6px; border-radius:4px;'>" + category + "</span></td>" +
                    "      </tr>" +
                    "      <tr>" +
                    "        <td style='padding: 5px 0; font-size: 13px; color: #6c757d; vertical-align: middle;'>AI Confidence Index:</td>" +
                    "        <td style='padding: 5px 0; font-size: 13px; font-weight: bold; color: #212529; vertical-align: middle;'>" +
                    "          <div style='display: flex; align-items: center; gap: 10px;'>" +
                    "            <div style='width: 150px; background: #e9ecef; border-radius: 10px; height: 10px; overflow: hidden; position: relative;'>" +
                    "              <div style='width: " + confidence + "%; background: linear-gradient(90deg, #ffc107, #28a745); height: 100%; border-radius: 10px;'></div>" +
                    "            </div>" +
                    "            <span>" + confidence + "%</span>" +
                    "          </div>" +
                    "        </td>" +
                    "      </tr>" +
                    "    </table>" +
                    "    <div style='background: #fff9db; border-left: 4px solid #ffc107; padding: 12px; border-radius: 0 4px 4px 0; font-size: 13px; color: #664d03; margin-bottom: 12px; line-height: 1.5;'>" +
                    "      <strong>🔎 Root Cause Assessment:</strong><br/>" + diagnosis.replace("\n", "<br/>") + "" +
                    "    </div>" +
                    "    <div style='background: #d4edda; border-left: 4px solid #28a745; padding: 12px; border-radius: 0 4px 4px 0; font-size: 13px; color: #155724; line-height: 1.5;'>" +
                    "      <strong>⚡ Prescriptive Resolution Fix:</strong><br/>" + fix.replace("\n", "<br/>") + "" +
                    "    </div>" +
                    "  </div>" +
                    "</div>";

                ExtentTestManager.getTest().log(Status.INFO, advancedDashboardHtml);
            }
        } catch (Exception e) {
            System.out.println("[AI LISTENER] Extent injection failed: " + e.getMessage());
        }
    }

    @Override 
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        // Strict checks prevent single-browser sessions from resetting a failed state into passed
        if (result.getStatus() != ITestResult.SUCCESS || BaseTest.failedTestNames.contains(testName)) {
            System.out.println("[AI LISTENER] Intercepted invalid PASS state for failed test: " + testName);
            return;
        }
        
        if (ExtentTestManager.getTest() != null) {
            if (ExtentTestManager.getTest().getModel().getStatus() != Status.FAIL) {
                ExtentTestManager.getTest().assignCategory("Success");
                ExtentTestManager.getTest().pass("Test Passed");
            }
        }
    }

    @Override 
    public void onTestSkipped(ITestResult result) {
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().skip("Test Skipped");
        }
    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onFinish(ITestContext context) {}
}