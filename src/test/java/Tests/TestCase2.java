package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.AccountDeletedPage;
import Pages.HomePage;
import Pages.SignUp_LogInPage;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/*
   Test Case 2: Login User with correct email and password

   Test Steps
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'Login to your account' is visible
6. Enter correct email address and password
7. Click 'login' button
8. Verify that 'Logged in as username' is visible
9. Click 'Delete Account' button
10. Verify that 'ACCOUNT DELETED!' is visible
 */

public class TestCase2 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    SignUp_LogInPage signUpLogInPage;
    AccountDeletedPage accountDeletedPage;

    @BeforeMethod
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver=openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork=new SeleniumFrameWork(driver);
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully(), Utils.Constants.
                HOME_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @Test(description = "Login User with correct email and password")
    public void testLogInUserWithCorrectEmailAndPassword(){

        signUpLogInPage=homePage.clickSignUp_LogIn();
        Assert.assertTrue(signUpLogInPage.checkLoggingToYourAccountMassageIsVisible(),Utils.Constants
                .LOGGING_TO_YOUR_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage=signUpLogInPage.logInWithValidEmailAndPassword();
        Assert.assertTrue( homePage.checkLoggedInAsUserNameIsVisible(),Utils.Constants
                .LOGGED_IN_AS_USER_NAME_IS_NOT_VISIBLE_SUCCESSFULLY);
        accountDeletedPage=homePage.clickDeleteAccount();
        Assert.assertTrue(accountDeletedPage.checkDeleteAccountMassageIsVisible(),Utils.Constants
                .DELETE_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @AfterMethod
    public void endOfTheTest(){

        myFrameWork.closeBrowser();

    }

}
