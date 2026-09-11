package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.*;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

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

    @BeforeMethod
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver = openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork = new SeleniumFrameWork(driver);
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully(), Utils.Constants.
                HOME_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @Test
    public void  testLogoutUser(){

        signUpLogInPage=homePage.clickSignUp_LogIn();
        Assert.assertTrue(signUpLogInPage.checkLoggingToYourAccountMassageIsVisible(),Utils.Constants
                .LOGGING_TO_YOUR_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage = signUpLogInPage.logInWithMyEmailAndPassword();
        Assert.assertTrue(homePage.checkLoggedInAsUserNameIsVisible(),Utils.Constants
                .LOGGED_IN_AS_USER_NAME_IS_NOT_VISIBLE_SUCCESSFULLY);
        signUpLogInPage=homePage.clickLogOutAccount();
        Assert.assertTrue(signUpLogInPage.UserInSignUp_LogInPage(),Utils.Constants
                .SIGNUP_LOGIN_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @AfterMethod
    public void endOfTheTest(){

        myFrameWork.closeBrowser();

    }

}