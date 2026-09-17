package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.*;
import Utilities.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/*
   Test Case 24: Download Invoice after purchase order
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Add products to cart
5. Click 'Cart' button
6. Verify that cart page is displayed
7. Click Proceed To Checkout
8. Click 'Register / Login' button
9. Fill all details in Signup and create account
10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
11. Verify ' Logged in as username' at top
12.Click 'Cart' button
13. Click 'Proceed To Checkout' button
14. Verify Address Details
15. Enter description in comment text area and click 'Place Order'
16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
17. Click 'Pay and Confirm Order' button
18. Click 'Download Invoice' button and verify invoice is downloaded successfully.
19. Click 'Continue' button
20. Click 'Delete Account' button
21. Verify 'ACCOUNT DELETED!' and click 'Continue' button
 */

@Epic("Automation Exercise Website")
@Feature("Checkout & Place Order")
public class TestCase24 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    CartPage cartPage;
    CheckOutPage checkOutPage;
    SignUp_LogInPage signUpLogInPage;
    EnterAccountInformation enterAccountInformation;
    AccountCreatedPage accountCreatedPage;
    PaymentPage paymentPage;
    OrderPlacedPage orderPlacedPage;
    AccountDeletedPage accountDeletedPage;

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
    @Story("Download Invoice after placing an order")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies the complete purchase flow and that the invoice is downloaded successfully after placing the order")
    public void testDownloadInvoiceAfterPurchaseOrder() throws InterruptedException {

        homePage.addProductToCart();
        cartPage = homePage.clickViewCart();
        Assert.assertTrue(cartPage.userIsInCartPage(),Utils.Constants
                .CART_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        checkOutPage = cartPage.clickProceedToCheckOut();
        signUpLogInPage = cartPage.clickLinkRegister_LogIn();
        enterAccountInformation = signUpLogInPage.signUp();
        accountCreatedPage = enterAccountInformation.createAccount();
        Assert.assertTrue(accountCreatedPage.checkAccountCreatedMassageIsVisible(),Utils.Constants
                .ACCOUNT_CREATED_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage = accountCreatedPage.clickContinueButton();
        Assert.assertTrue(homePage.checkLoggedInAsUserNameIsVisible(),Utils.Constants
                .LOGGED_IN_AS_USER_NAME_IS_NOT_VISIBLE_SUCCESSFULLY);
        cartPage = homePage.clickCartPage();
        checkOutPage = cartPage.clickProceedToCheckOut();
        Assert.assertTrue(checkOutPage.checkTheDeliveryAddress(),Utils.Constants
                .THE_DELIVERY_ADDRESS_IS_NOT_SIMILAR_TO_REGISTERED_ADDRESS);
        Assert.assertTrue(checkOutPage.checkTheBillingAddress(),Utils.Constants
                .THE_BILLING_ADDRESS_IS_NOT_SIMILAR_TO_REGISTERED_ADDRESS);
        checkOutPage.addComment();
        paymentPage = checkOutPage.clickPlaceOrder();
        orderPlacedPage = paymentPage.enterPaymentDetailsAndClickConfirmOrder();
        orderPlacedPage.clickDownloadInvoice();
        Assert.assertTrue(orderPlacedPage.isInvoiceFileDownloaded(),Utils.Constants
                .INVOICE_WAS_NOT_DOWNLOADED_SUCCESSFULLY);
        homePage = orderPlacedPage.clickContinue();
        accountDeletedPage = homePage.clickDeleteAccount();
        Assert.assertTrue(accountDeletedPage.checkDeleteAccountMassageIsVisible(),Utils.Constants
                .DELETE_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage = accountDeletedPage.clickContinueButton();

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }

}
