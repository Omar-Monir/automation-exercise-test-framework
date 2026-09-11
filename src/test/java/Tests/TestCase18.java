package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.CategoryPage;
import Pages.HomePage;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/*
   Test Case  18: View Category Products
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that categories are visible on left sidebar
4. Click on 'Women' category
5. Click on any category link under 'Women' category, for example: Dress
6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
7. On left sidebar, click on any sub-category link of 'Men' category
8. Verify that user is navigated to that category page
 */

public class TestCase18 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    CategoryPage categoryPage;

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
    public void testViewCategoryProducts(){

        homePage.scrollToFirstProductView();
        Assert.assertTrue(homePage.checkThatCategoriesAreVisible(),Utils.Constants
                .CATEGORIES_IS_NOT_VISIBLE_SUCCESSFULLY);
        categoryPage = homePage.clickOnTheDress();
        Assert.assertTrue(categoryPage.checkUserInCategoryPage(),Utils.Constants
                .CART_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        categoryPage.clickOnTShirts();
        Assert.assertTrue(categoryPage.checkUserInCategoryPage(),Utils.Constants
                .CATEGORY_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }

}
