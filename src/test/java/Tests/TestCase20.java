package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import Pages.SignUp_LogInPage;
import Utilities.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/*
   Test Case 20: Search Products and Verify Cart After Login
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Click on 'Products' button
4. Verify user is navigated to ALL PRODUCTS page successfully
5. Enter product name in search input and click search button
6. Verify 'SEARCHED PRODUCTS' is visible
8. Add those products to cart
9. Click 'Cart' button and verify that products are visible in cart
10. Click 'Signup / Login' button and submit login details
11. Again, go to Cart page
12. Verify that those products are visible in cart after login as well
 */

@Epic("Automation Exercise Website")
@Feature("Products & Cart")
public class TestCase20 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;
    CartPage cartPage;
    SignUp_LogInPage signUpLogInPage;

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
    @Story("Search products and verify cart after login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that searched products remain in the cart even after the user logs in")
    public void testSearchProductsAndVerifyCartAfterLogin(){

        productsPage = homePage.clickProducts();
        Assert.assertTrue(productsPage.verifyThatAllProductsPageIsVisibleSuccessfully(),Utils.Constants
                .ALL_PRODUCTS_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        productsPage.searchForProduct();
        Assert.assertTrue(productsPage.verifyThatSearchedProductsMessageIsVisibleSuccessfully(),Utils.Constants
                .SEARCHED_PRODUCTS_MESSAGE_IS_NOT_VISIBLE);
        productsPage.clickAddImgSleeveLessDreesProductToCart();
        cartPage = productsPage.clickViewCart();
        Assert.assertTrue( cartPage.checkSleeveLessProductIsAdded(),Utils.Constants
                .SLEEVE_LESS_PRODUCT_IS_NOT_ADDED_SUCCESSFULLY);
        signUpLogInPage = cartPage.clickSignUp_LogIn();
        homePage = signUpLogInPage.logInWithRealEmailAndPassword();
        homePage.clickCartPage();
        Assert.assertTrue( cartPage.checkSleeveLessProductIsAdded(),Utils.Constants
                .SLEEVE_LESS_PRODUCT_IS_NOT_ADDED_SUCCESSFULLY);

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
