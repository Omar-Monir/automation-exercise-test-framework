package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.CartPage;
import Pages.HomePage;
import Utilities.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Epic("Automation Exercise Website")
@Feature("Products")
/*
   Test Case 22: Add to cart from Recommended items
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Scroll to bottom of page
4. Verify 'RECOMMENDED ITEMS' are visible
5. Click on 'Add To Cart' on Recommended product
6. Click on 'View Cart' button
7. Verify that product is displayed in cart page
 */

public class TestCase22 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    CartPage cartPage;

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
    @Story("Add product to cart from Recommended Items")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that the user can add a product to the cart from the Recommended Items section")
    public void testAddToCartFromRecommendedItems(){

        homePage.scrollToHomeSubscription();
        Assert.assertTrue(homePage.checkThatRecommendedItemsAreVisible(),Utils.Constants
                .RECOMMENDED_ITEMS_ARE_NOT_APPEARED);
        homePage.clickOnRecommendedItemStylishDress();
        cartPage = homePage.clickViewCart();
        Assert.assertTrue(cartPage.checkStylishDressProductIsAdded(),Utils.Constants
                .STYLISH_DREES_PRODUCT_IS_NOT_ADDED_SUCCESSFULLY);

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }

}
