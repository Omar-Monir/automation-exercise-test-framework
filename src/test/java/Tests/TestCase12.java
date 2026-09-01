package Tests;

/*
   Test Case 12: Add Products in Cart
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click 'Products' button
5. Hover over first product and click 'Add to cart'
6. Click 'Continue Shopping' button
7. Hover over second product and click 'Add to cart'
8. Click 'View Cart' button
9. Verify both products are added to Cart
10. Verify their prices, quantity and total price
 */

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase12 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;
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
    public void  testAddProductsInCart(){

        productsPage = homePage.clickProducts();
        productsPage.scrollTo4thProduct();
        productsPage.clickAddFirstProductToCart();
        productsPage.clickContinue();
        productsPage.clickAddSecondProductToCart();
        cartPage = productsPage.clickViewCart();
        Assert.assertTrue(cartPage.checkFirstProductIsAdded());
        Assert.assertTrue(cartPage.checkSecondProductIsAdded());

    }

    @AfterTest
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
