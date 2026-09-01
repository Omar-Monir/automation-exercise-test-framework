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
   Test Case 5: Register User with existing email

   Test Steps
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'New User Signup!' is visible
6. Enter name and already registered email address
7. Click 'Signup' button
8. Verify error 'Email Address already exist!' is visible
 */

public class TestCase5 extends BrowserManager {

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
    public void testLogoutUser() {

        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully());
        signUpLogInPage = homePage.clickSignUp_LogIn();
        Assert.assertTrue(signUpLogInPage.checkNewUserSignupMassageIsVisible());
        signUpLogInPage.signUpWithMyEmail();
        Assert.assertTrue(signUpLogInPage.checkEmailAddressAlreadyExistMassageIsVisible());

    }

    @AfterTest
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }

}
