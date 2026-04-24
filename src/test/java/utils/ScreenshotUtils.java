package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        String folderPath = "test-output/screenshots/";
        String fileName = testName + "_" + timestamp + ".png";
        String fullPath = folderPath + fileName;

        try {

            // Create folder if not exists
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File src =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, new File(fullPath));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Important: return relative path for Extent
        return "screenshots/" + fileName;
    }
}