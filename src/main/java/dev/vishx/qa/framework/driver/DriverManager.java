package dev.vishx.qa.framework.driver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    
    public static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
    public static  void createDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");
            options.setDeviceName("emulator-5554");
            AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
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
