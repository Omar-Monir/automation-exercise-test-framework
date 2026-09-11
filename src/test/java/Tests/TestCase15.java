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
   Test Case 15: Place Order: Register before Checkout
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click 'Signup / Login' button
5. Fill all details in Signup and create account
6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
7. Verify ' Logged in as username' at top
8. Add products to cart
9. Click 'Cart' button
10. Verify that cart page is displayed
11. Click Proceed To Checkout
12. Enter description in comment text area and click 'Place Order'
13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
14. Click 'Pay and Confirm Order' button
15. Click 'Delete Account' button
16. Verify 'ACCOUNT DELETED!' and click 'Continue' button
 */

public class TestCase15 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    SignUp_LogInPage signUpLogInPage;
    EnterAccountInformation enterAccountInformation;
    AccountCreatedPage accountCreatedPage;
    CartPage cartPage;
    CheckOutPage checkOutPage;
    PaymentPage paymentPage;
    OrderPlacedPage orderPlacedPage;
    AccountDeletedPage accountDeletedPage;

    @BeforeMethod

    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver = openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork = new SeleniumFrameWork(driver);
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully(),Utils.Constants
                .HOME_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @Test
    public void testPlaceOrder_RegisterBeforeCheckout(){

        signUpLogInPage = homePage.clickSignUp_LogIn();
        enterAccountInformation = signUpLogInPage.signUp();
        accountCreatedPage = enterAccountInformation.createAccount();
        Assert.assertTrue(accountCreatedPage.checkAccountCreatedMassageIsVisible(), Utils.Constants
                .ACCOUNT_CREATED_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage = accountCreatedPage.clickContinueButton();
        Assert.assertTrue(homePage.checkLoggedInAsUserNameIsVisible(),Utils.Constants
                .LOGGED_IN_AS_USER_NAME_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage.addProductToCart();
        cartPage = homePage.clickViewCart();
        Assert.assertTrue(cartPage.userIsInCartPage(),Utils.Constants.CART_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        checkOutPage = cartPage.clickProceedToCheckOut();
        checkOutPage.goToCheckOutFooter();
        checkOutPage.addComment();
        paymentPage = checkOutPage.clickPlaceOrder();
        orderPlacedPage = paymentPage.enterPaymentDetailsAndClickConfirmOrder();
        accountDeletedPage = orderPlacedPage.clickDeleteAccount();
        Assert.assertTrue(accountDeletedPage.checkDeleteAccountMassageIsVisible(),Utils.Constants
                .DELETE_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        accountDeletedPage.clickContinueButton();

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
