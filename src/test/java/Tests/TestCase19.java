package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.BrandPage;
import Pages.HomePage;
import Pages.ProductsPage;
import Utilities.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/*
   Test Case 19: View & Cart Brand Products
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Click on 'Products' button
4. Verify that Brands are visible on left sidebar
5. Click on any brand name
6. Verify that user is navigated to brand page and brand products are displayed
7. On left sidebar, click on any other brand link
8. Verify that user is navigated to that brand page and can see products
 */

@Epic("Automation Exercise Website")
@Feature("Brand")
public class TestCase19 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ProductsPage productsPage;
    BrandPage brandPage;

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
    @Story("View Brand products")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that the user can view products under different brands")
    public void testView_CartBrandProducts(){

        productsPage = homePage.clickProducts();
        productsPage.scrollToBiba();
        Assert.assertTrue(productsPage.checkBrandIsVisible(), Utils.Constants
                .BRAND_IS_NOT_VISIBLE_SUCCESSFULLY);
        brandPage = productsPage.clickOnBiba();
        Assert.assertTrue(brandPage.UserInBrandPage(),Utils.Constants.BRAND_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        brandPage.clickOnMadame();
        Assert.assertTrue(brandPage.UserInBrandPage(),Utils.Constants.BRAND_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }

}
