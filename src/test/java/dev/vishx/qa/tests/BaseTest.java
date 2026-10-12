package dev.vishx.qa.tests;

import dev.vishx.qa.framework.driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp(){
        DriverManager.createDriver();
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quitDriver();
    }
    
}
