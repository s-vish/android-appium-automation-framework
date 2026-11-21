package dev.vishx.qa.framework.helpers;

import dev.vishx.qa.framework.config.ConfigReader;
import dev.vishx.qa.framework.config.FrameworkConfig;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final AndroidDriver driver;
    private final ConfigReader configReader = FrameworkConfig.getConfigReader();
    public WaitHelper(AndroidDriver driver){
        this.driver=driver;
    }

    private WebDriverWait getWait(WaitType waitType){

        int timeoutInSeconds = switch (waitType){
            case SHORT -> configReader.getInt("shortWaitSeconds");
            case MEDIUM -> configReader.getInt("mediumWaitSeconds");
            case LONG -> configReader.getInt("longWaitSeconds");
        };
        if(timeoutInSeconds<=0){
            throw new IllegalArgumentException("Wait timeout must be greater than 0, but was "+timeoutInSeconds);
        }
        return new WebDriverWait(driver,Duration.ofSeconds(timeoutInSeconds));
    }

    public WebElement waitForVisibility(By locator, WaitType waitType){

        return getWait(waitType).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickability(By locator, WaitType waitType){
        return getWait(waitType).until(ExpectedConditions.elementToBeClickable(locator));
    }
    public WebElement waitForPresence(By locator, WaitType waitType){
        return getWait(waitType).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public boolean waitForInvisibility(By locator, WaitType waitType){
        return getWait(waitType).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}
