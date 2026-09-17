package Tests;

/*
   Test Case  21: Add review on product
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Click on 'Products' button
4. Verify user is navigated to ALL PRODUCTS page successfully
5. Click on 'View Product' button
6. Verify 'Write Your Review' is visible
7. Enter name, email and review
8. Click 'Submit' button
9. Verify success message 'Thank you for your review.'
 */

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.HomePage;
import Pages.ProductDetailsPage;
import Pages.ProductsPage;
import Utilities.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Epic("Automation Exercise Website")
@Feature("Products")
public class TestCase21 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;

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
    @Story("Add review on product")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that a user can successfully submit a review on a product")
    public void testAddReviewOnProduct(){

        productsPage = homePage.clickProducts();
        Assert.assertTrue(productsPage.verifyThatAllProductsPageIsVisibleSuccessfully(),Utils.Constants
                .ALL_PRODUCTS_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        productDetailsPage = productsPage.clickViewSleeveLessDreesProduct();
        Assert.assertTrue(productDetailsPage.checkWriteYourReviewIsVisible(),Utils.Constants
                .WRITE_YOUR_REVIEW_IS_NOT_VISIBLE_SUCCESSFULLY);
        productDetailsPage.clickSubmitReviewButton();
        Assert.assertTrue(productDetailsPage.checkTheReviewMessageIsAppeared(),Utils.Constants
                .REVIEW_MESSAGE_NOT_APPEARED);
    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }

}
