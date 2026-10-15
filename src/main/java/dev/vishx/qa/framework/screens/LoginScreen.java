package dev.vishx.qa.framework.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {

    private  final By usernameField = AppiumBy.accessibilityId("test-Username");
    private  final By passwordField= AppiumBy.accessibilityId("test-Password");
    private  final By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    public LoginScreen(AndroidDriver driver){
        super(driver);
    }

    public void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public HomeScreen clickLogin(){
        driver.findElement(loginButton).click();
        return new HomeScreen(driver);
    }
}
