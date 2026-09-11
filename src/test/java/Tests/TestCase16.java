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
   Test Case 16: Place Order: Login before Checkout
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click 'Signup / Login' button
5. Fill email, password and click 'Login' button
6. Verify 'Logged in as username' at top
7. Add products to cart
8. Click 'Cart' button
9. Verify that cart page is displayed
10. Click Proceed To Checkout
11. Enter description in comment text area and click 'Place Order'
12. Enter payment details: Name on Card, Card Number, CVC, Expiration date
13. Click 'Pay and Confirm Order' button
 */

public class TestCase16 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    SignUp_LogInPage signUpLogInPage;
    CartPage cartPage;
    CheckOutPage checkOutPage;
    PaymentPage paymentPage;
    OrderPlacedPage orderPlacedPage;

    @BeforeMethod
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver = openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork = new SeleniumFrameWork(driver);
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully(), Utils.Constants
                .HOME_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @Test
    public void testPlaceOrderLoginBeforeCheckout(){

        signUpLogInPage = homePage.clickSignUp_LogIn();
        homePage = signUpLogInPage.logInWithMyEmailAndPassword();
        Assert.assertTrue(homePage.checkLoggedInAsUserNameIsVisible(),Utils.Constants
                .LOGGED_IN_AS_USER_NAME_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage.addProductToCart();
        cartPage = homePage.clickViewCart();
        Assert.assertTrue(cartPage.userIsInCartPage(),Utils.Constants
                .CART_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
         checkOutPage = cartPage.clickProceedToCheckOut();
         checkOutPage.goToCheckOutFooter();
         checkOutPage.addComment();
         paymentPage = checkOutPage.clickPlaceOrder();
         paymentPage.enterPaymentDetailsAndClickConfirmOrder();

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
