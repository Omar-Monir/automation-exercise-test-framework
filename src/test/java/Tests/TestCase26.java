package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.HomePage;
import Utilities.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/*
   Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Scroll down page to bottom
5. Verify 'SUBSCRIPTION' is visible
6. Scroll up page to top
7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
 */

@Epic("Automation Exercise Website")
@Feature("UI Functionality")
public class TestCase26 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;

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
    @Story("Scroll up without Arrow button")
    @Severity(SeverityLevel.MINOR)
    @Description("This test verifies that the user can scroll down and then scroll up without using the arrow button")
    public void testVerifyScrollUpWithoutArrowButtonAndScrollDownFunctionality(){

        homePage.scrollToHomeFooter();
        Assert.assertTrue(homePage.verifyHomeTextSubscription(),Utils.Constants
                .HOME_SUBSCRIPTION_MESSAGE_IS_NOT_VISIBLE_SUCCESSFULLY);
        homePage.scrollToHomeHeader();
        Assert.assertTrue(homePage.checkSlider_CarouselText(),Utils.Constants
                .SLIDER_CAROUSEL_TEXT_NO_CORRECT);

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }
}
