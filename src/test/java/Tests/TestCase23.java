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
   Test Case 23: Verify address details in checkout page
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
12. Verify that the delivery address is same address filled at the time registration of account
13. Verify that the billing address is same address filled at the time registration of account
14. Click 'Delete Account' button
15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
 */

@Epic("Automation Exercise Website")
@Feature("Checkout & Place Order")
public class TestCase23 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    SignUp_LogInPage signUpLogInPage;
    EnterAccountInformation enterAccountInformation;
    AccountCreatedPage accountCreatedPage;
    CartPage cartPage;
    CheckOutPage checkOutPage;
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
    @Story("Verify address details in checkout page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that the delivery and billing addresses on the checkout page match the address entered during registration")
    public void testVerifyAddressDetailsInCheckoutPage(){

        signUpLogInPage = homePage.clickSignUp_LogIn();
        enterAccountInformation = signUpLogInPage.signUp();
        accountCreatedPage = enterAccountInformation.createAccount();
        Assert.assertTrue(accountCreatedPage.checkAccountCreatedMassageIsVisible(),Utils.Constants
                .ACCOUNT_CREATED_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage = accountCreatedPage.clickContinueButton();
        Assert.assertTrue(homePage.checkLoggedInAsUserNameIsVisible(),Utils.Constants
                .LOGGED_IN_AS_USER_NAME_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage.addProductToCart();
        cartPage = homePage.clickViewCart();
        Assert.assertTrue(cartPage.userIsInCartPage(),Utils.Constants
                .CART_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        checkOutPage = cartPage.clickProceedToCheckOut();
        Assert.assertTrue(checkOutPage.checkTheDeliveryAddress(),Utils.Constants
                .THE_DELIVERY_ADDRESS_IS_NOT_SIMILAR_TO_REGISTERED_ADDRESS);
        Assert.assertTrue(checkOutPage.checkTheBillingAddress(),Utils.Constants
                .THE_BILLING_ADDRESS_IS_NOT_SIMILAR_TO_REGISTERED_ADDRESS);
        accountDeletedPage = checkOutPage.clickDeleteAccount();
        Assert.assertTrue(accountDeletedPage.checkDeleteAccountMassageIsVisible(),Utils.Constants
                .DELETE_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage = accountDeletedPage.clickContinueButton();

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }

}
