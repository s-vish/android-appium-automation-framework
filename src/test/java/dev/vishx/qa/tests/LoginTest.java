package dev.vishx.qa.tests;

import dev.vishx.qa.framework.screens.HomeScreen;
import dev.vishx.qa.framework.screens.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void verifySuccessfulLogin(){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.enterUsername("standard_user");
        loginScreen.enterPassword("secret_sauce");
        HomeScreen homeScreen = loginScreen.clickLogin();
        Assert.assertTrue(homeScreen.isDisplayed(),"Home screen was not displayed after successful login");
    }

    @Test
    public void verifyFailedLoginWithInvalidCreds(){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.enterUsername("standard_userss");
        loginScreen.enterPassword("secret_sauce");
        loginScreen.clickLogin();
        String errorMessage = loginScreen.getLoginErrorMessage();
        Assert.assertEquals(errorMessage,"Username and password do not match any user in this service.");

    }
}
