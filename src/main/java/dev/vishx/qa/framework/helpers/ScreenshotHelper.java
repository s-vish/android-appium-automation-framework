package dev.vishx.qa.framework.helpers;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotHelper {
    public Path captureScreenshot(AndroidDriver driver, String fileName){
        try{
            Path screenshotDirectory = Path.of("target", "screenshots");
            Files.createDirectories(screenshotDirectory);
            Path screenshotPath = screenshotDirectory.resolve(fileName+".png");
            Path temporaryScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE).toPath();
            return Files.copy(temporaryScreenshot,screenshotPath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new RuntimeException("Failed to save screenshot: " + fileName, e);
        }
    }
}
