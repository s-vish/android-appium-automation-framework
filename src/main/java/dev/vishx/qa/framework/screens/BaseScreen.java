package dev.vishx.qa.framework.screens;

import dev.vishx.qa.framework.helpers.WaitHelper;
import io.appium.java_client.android.AndroidDriver;

public class BaseScreen {

    protected  final AndroidDriver driver;
    protected final WaitHelper waitHelper;

    public BaseScreen(AndroidDriver driver){
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }
}
