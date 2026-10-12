package dev.vishx.qa.tests;

import org.testng.annotations.Test;

public class DriverTest extends BaseTest{
    @Test
    public void verifyAppiumSession(){
        System.out.println("Session started successfully");
        System.out.println("Session Id: "+driver.getSessionId());
        System.out.println("Device: "+driver.getCapabilities().getCapability("deviceName"));
    }
}
