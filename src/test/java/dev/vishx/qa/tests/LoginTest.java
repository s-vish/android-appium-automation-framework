package dev.vishx.qa.tests;

import dev.vishx.qa.framework.data.TestDataReader;
import dev.vishx.qa.framework.screens.HomeScreen;
import dev.vishx.qa.framework.screens.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void verifySuccessfulLogin(){
        TestDataReader testDataReader = new TestDataReader("testdata/authentication.json");
        String username = testDataReader.getValue("validLogin","username");
        String password = testDataReader.getValue("validLogin","password");
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.enterUsername(username);
        loginScreen.enterPassword(password);
        HomeScreen homeScreen = loginScreen.clickLogin();
        Assert.assertTrue(homeScreen.isDisplayed(),"Home screen was not displayed after successful login");
    }

    @Test
    public void verifyFailedLoginWithInvalidCreds(){
        TestDataReader testDataReader = new TestDataReader("testdata/authentication.json");
        String invalidUsername = testDataReader.getValue("invalidLogin","username");
        String invalidPassword = testDataReader.getValue("invalidLogin","password");
        String errorMessage = testDataReader.getValue("invalidLogin","expectedError");
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.enterUsername(invalidUsername);
        loginScreen.enterPassword(invalidPassword);
        loginScreen.clickLogin();
        String actualErrorMessage = loginScreen.getLoginErrorMessage();
        Assert.assertEquals(actualErrorMessage,errorMessage, "Unexpected error message for invalid credentials");

    }
}
