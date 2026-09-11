package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductDetailsPage;
import Pages.ProductsPage;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/*
   Test Case 13: Verify Product quantity in Cart
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click 'View Product' for any product on home page
5. Verify product detail is opened
6. Increase quantity to 4
7. Click 'Add to cart' button
8. Click 'View Cart' button
9. Verify that product is displayed in cart page with exact quantity
 */

public class TestCase13 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;



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
    public void testProductQuantityInCart(){

        productsPage = homePage.clickProducts();
        productsPage.scrollTo4thProduct();
        productDetailsPage = productsPage.clickViewFirstProduct();
        Assert.assertTrue(productDetailsPage.verifyThatProductDetailsPageIsVisibleSuccessfully(),Utils.Constants
                .PRODUCTS_DETAILS_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        productDetailsPage.setQuantity();
        productDetailsPage.clickAddToCart();
        cartPage = productDetailsPage.clickViewCart();
        Assert.assertTrue(cartPage.checkTheQuantityOfTheProduct(),Utils.Constants
                .QUANTITY_OF_PRODUCT_IS_NOT_RIGHT);

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
