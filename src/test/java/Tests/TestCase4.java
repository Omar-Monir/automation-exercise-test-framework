package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/*
   Test Case 4: Logout User

   Test Steps
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'Login to your account' is visible
6. Enter correct email address and password
7. Click 'login' button
8. Verify that 'Logged in as username' is visible
9. Click 'Logout' button
10. Verify that user is navigated to login page
 */

public class TestCase4 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    SignUp_LogInPage signUpLogInPage;

    @BeforeTest
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver = openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    @Test
    public void  testLogoutUser(){

        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully());
        signUpLogInPage=homePage.clickSignUp_LogIn();
        signUpLogInPage.checkLoggingToYourAccountMassageIsVisible();
        homePage = signUpLogInPage.logInWithMyEmailAndPassword();
        Assert.assertTrue(homePage.checkLoggedInAsUserNameIsVisible());
        signUpLogInPage=homePage.clickLogOutAccount();
        Assert.assertTrue(signUpLogInPage.UserInSignUp_LogInPage());

    }

    @AfterTest
    public void endOfTheTest(){

        myFrameWork.closeBrowser();

    }

}