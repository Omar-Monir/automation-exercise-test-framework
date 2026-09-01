package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.AccountDeletedPage;
import Pages.HomePage;
import Pages.SignUp_LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/*
   Test Case 3: Login User with incorrect email and password

   Test Steps
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'Login to your account' is visible
6. Enter incorrect email address and password
7. Click 'login' button
8. Verify error 'Your email or password is incorrect!' is visible
 */


public class TestCase3 extends BrowserManager {

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
    public void testLoginUserWithIncorrectEmailAndPassword(){

        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully());
        signUpLogInPage=homePage.clickSignUp_LogIn();
        Assert.assertTrue(signUpLogInPage.checkLoggingToYourAccountMassageIsVisible());
        signUpLogInPage.logInWithInValidEmailAndPassword();
        Assert.assertTrue(signUpLogInPage.checkEmailOrPasswordIsNotCorrectMessageIsVisible());

    }

    @AfterTest
    public void endOfTheTest(){

        myFrameWork.closeBrowser();

    }



}
