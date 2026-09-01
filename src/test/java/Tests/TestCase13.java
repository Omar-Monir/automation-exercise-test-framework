package Tests;

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

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductDetailsPage;
import Pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase13 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;



    @BeforeTest
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver = openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork = new SeleniumFrameWork(driver);
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully());

    }

    @Test
    public void testProductQuantityInCart(){

        productsPage = homePage.clickProducts();
        productsPage.scrollTo4thProduct();
        productDetailsPage = productsPage.clickViewFirstProduct();
        Assert.assertTrue(productDetailsPage.verifyThatProductDetailsPageIsVisibleSuccessfully());
        productDetailsPage.setQuantity();
        productDetailsPage.clickAddToCart();
        cartPage = productDetailsPage.clickViewCart();
        Assert.assertTrue(cartPage.checkTheQuantityOfTheProduct());

    }

    @AfterTest
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
