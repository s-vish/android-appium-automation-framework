package dev.vishx.qa.framework.driver;
import io.appium.java_client.android.AndroidDriver;

public class DriverManager {
    
    public static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static void createDriver() {
            AndroidDriver androidDriver = DriverFactory.createDriver();
            driver.set(androidDriver);
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
