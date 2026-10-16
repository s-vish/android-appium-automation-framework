package dev.vishx.qa.framework.driver;
import dev.vishx.qa.framework.config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    
    public static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    private static final ConfigReader configReader = new ConfigReader();
    public static  void createDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName(configReader.get("platform"));
            options.setAutomationName(configReader.get("automationName"));
            options.setDeviceName(configReader.get("deviceName"));

            options.setAppPackage(configReader.get("appPackage"));
            options.setAppActivity(configReader.get("appActivity"));
            AndroidDriver androidDriver = new AndroidDriver(new URL(configReader.get("appiumServerUrl")), options);
            driver.set(androidDriver);
        }
        catch (MalformedURLException e){
            throw new RuntimeException("Invalid Appium server URL",e);
        }
    }

    public static AndroidDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){
        AndroidDriver currentDriver = driver.get();

        if(currentDriver!=null){
            currentDriver.quit();
            driver.remove();
        }
    }
}
