package dev.vishx.qa.framework.driver;

import dev.vishx.qa.framework.config.ConfigReader;
import dev.vishx.qa.framework.config.FrameworkConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static final ConfigReader configReader = FrameworkConfig.getConfigReader();

    public static AndroidDriver createDriver(){
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName(configReader.get("platform"));
            options.setAutomationName(configReader.get("automationName"));
            options.setDeviceName(configReader.get("deviceName"));

            options.setAppPackage(configReader.get("appPackage"));
            options.setAppActivity(configReader.get("appActivity"));
            AndroidDriver androidDriver = new AndroidDriver(new URL(configReader.get("appiumServerUrl")), options);
            return androidDriver;
        }
        catch (MalformedURLException e){
            throw new RuntimeException("Invalid Appium server URL",e);
        }

    }

}
