package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import Utilities.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

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

@Epic("Automation Exercise Website")
@Feature("Cart")
public class TestCase12 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;
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
    @Story("Add multiple products to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that the user can add multiple products to the cart successfully")
    public void  testAddProductsInCart(){

        productsPage = homePage.clickProducts();
        productsPage.scrollTo4thProduct();
        productsPage.clickAddImgSleeveLessDreesProductToCart();
        productsPage.clickContinue();
        productsPage.clickAddSecondProductToCart();
        cartPage = productsPage.clickViewCart();
        Assert.assertTrue(cartPage.checkSleeveLessProductIsAdded(),Utils.Constants
                .SLEEVE_LESS_PRODUCT_IS_NOT_ADDED_SUCCESSFULLY);
        Assert.assertTrue(cartPage.checkSecondProductIsAdded(),Utils.Constants
                .SECOND_PRODUCT_IS_NOT_ADDED_SUCCESSFULLY);

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
