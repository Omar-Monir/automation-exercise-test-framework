package Tests;

import MyFrameWork.SeleniumFrameWork;
import Pages.*;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import BrowserManager.*;

import java.io.IOException;


/*
   Test Case 1:Register User

   Test Steps
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'New User Signup!' is visible
6. Enter name and email address
7. Click 'Signup' button
8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
9. Fill details: Title, Name, Email, Password, Date of birth
10. Select checkbox 'Sign up for our newsletter!'
11. Select checkbox 'Receive special offers from our partners!'
12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
13. Click 'Create Account button'
14. Verify that 'ACCOUNT CREATED!' is visible
15. Click 'Continue' button
16. Verify that 'Logged in as username' is visible
17. Click 'Delete Account' button
18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
 */

public class TestCase1 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    SignUp_LogInPage signUpLogInPage;
    EnterAccountInformation enterAccountInformation;
    AccountCreatedPage accountCreatedPage;
    AccountDeletedPage accountDeletedPage;

    @BeforeMethod
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver=openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork=new SeleniumFrameWork(driver);
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully(), Utils.Constants
                .HOME_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @Test
    public void testRegisterUser() {

        signUpLogInPage = homePage.clickSignUp_LogIn();
        Assert.assertTrue(signUpLogInPage.checkNewUserSignupMassageIsVisible(),Utils.Constants
                .NEW_USER_SIGNUP_MASSAGE_IS_NOT_VISIBLE);
        enterAccountInformation =signUpLogInPage.signUp();
        Assert.assertTrue(enterAccountInformation.checkEnterAccountInformationMassageIsVisible(),Utils.Constants
                .ENTER_ACCOUNT_INFORMATION_IS_NOT_VISIBLE_SUCCESSFULLY);
        accountCreatedPage = enterAccountInformation.createAccount();
        Assert.assertTrue(accountCreatedPage.checkAccountCreatedMassageIsVisible(),Utils.Constants
                .ACCOUNT_CREATED_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage = accountCreatedPage.clickContinueButton();
        accountDeletedPage=homePage.clickDeleteAccount();
        Assert.assertTrue(accountDeletedPage.checkDeleteAccountMassageIsVisible(),Utils.Constants
                .DELETE_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        accountDeletedPage.clickContinueButton();

    }

    @AfterMethod
    public void endOfTheTest(){

        myFrameWork.closeBrowser();

    }

}
