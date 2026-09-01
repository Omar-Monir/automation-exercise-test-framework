package Tests;

/*
   Test Case 9: Search Product
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Products' button
5. Verify user is navigated to ALL PRODUCTS page successfully
6. Enter product name in search input and click search button
7. Verify 'SEARCHED PRODUCTS' is visible
 */

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.HomePage;
import Pages.ProductsPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase9 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;

    @BeforeTest
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver = openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork = new SeleniumFrameWork(driver);
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully());

    }

    @Test
    public void testSearchProduct(){

        productsPage = homePage.clickProducts();
        Assert.assertTrue(productsPage.verifyThatAllProductsPageIsVisibleSuccessfully());
        productsPage.searchForProduct();
        Assert.assertTrue(productsPage.verifyThatSearchedProductsMessageIsVisibleSuccessfully());

    }

    @AfterTest
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
