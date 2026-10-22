package dev.vishx.qa.tests;

import dev.vishx.qa.framework.driver.DriverManager;
import dev.vishx.qa.framework.helpers.ScreenshotHelper;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Path;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp(){
        DriverManager.createDriver();
        driver = DriverManager.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try{
            if(!result.isSuccess() && driver != null){
                ScreenshotHelper screenshotHelper =
                        new ScreenshotHelper();
                Path screenshotPath =
                        screenshotHelper.captureScreenshot(driver,
                                result.getMethod().getMethodName());
                System.out.println(
                        "Failure screenshot saved: "+screenshotPath);
            }
        }catch (Exception e){
            System.err.println("Failed to capture failure screenshot: "
                    +e.getMessage());
        }finally {
            DriverManager.quitDriver();
        }

    }
    
}
