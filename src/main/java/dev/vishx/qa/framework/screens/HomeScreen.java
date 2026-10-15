package dev.vishx.qa.framework.screens;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomeScreen extends BaseScreen{

    private  final By productsTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")");

    public HomeScreen(AndroidDriver driver){
        super(driver);
    }

    public boolean isDisplayed(){
        return waitHelper.waitForVisibility(productsTitle).isDisplayed();
    }
}
