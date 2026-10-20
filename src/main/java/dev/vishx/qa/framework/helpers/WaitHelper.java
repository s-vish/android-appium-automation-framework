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
    private  final WebDriverWait wait;
    private final ConfigReader configReader = FrameworkConfig.getConfigReader();
    public WaitHelper(AndroidDriver driver){
        int timeoutInSeconds = configReader.getInt("explicitWaitTimeoutSeconds");
        if(timeoutInSeconds<=0){
            throw new IllegalArgumentException("explicitWaitTimeoutSeconds must be greater than 0, but was: "+timeoutInSeconds);
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public WebElement waitForVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickability(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public WebElement waitForPresence(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public boolean waitForInvisibility(By locator){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}
