package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.ProductDetailsPage;
import Pages.HomePage;
import Pages.ProductsPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/*
   Test Case8: Verify All Products and product detail page
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Products' button
5. Verify user is navigated to ALL PRODUCTS page successfully
6. Click on 'View Product' of first product
7. User is landed to product detail page
8. Verify that detail is visible: product name, category, price, availability, condition, brand
 */


public class TestCase8 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailsPage firstProduct;

    @BeforeTest
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver = openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork = new SeleniumFrameWork(driver);
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully());

    }

    @Test
    public void testVerifyAllProductsAndProductDetailPage(){

        productsPage = homePage.clickProducts();
        Assert.assertTrue(productsPage.verifyThatAllProductsPageIsVisibleSuccessfully());
        firstProduct = productsPage.clickViewFirstProduct();
        Assert.assertTrue(firstProduct.verifyThatProductDetailsPageIsVisibleSuccessfully());
        Assert.assertTrue(firstProduct.verifyThatDetailIsVisible());

    }

    @AfterTest
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }



}
