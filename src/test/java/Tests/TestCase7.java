package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.*;
import Utilities.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

/*
   Test Case 7: Verify Test Cases Page
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Test Cases' button
5. Verify user is navigated to test cases page successfully
 */

@Epic("Automation Exercise Website")
@Feature("Navigation")
public class TestCase7 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    TestCasesPage testCasesPage;

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
    @Story("Verify Test Cases page")
    @Severity(SeverityLevel.MINOR)
    @Description("This test verifies that the user can navigate to the Test Cases page successfully")
    public void testVerifyTestCasesPage(){

        testCasesPage = homePage.clickTestCasesPage();
        Assert.assertTrue(testCasesPage.verifyInTestCasesPage(),Utils.Constants
                .TEST_CASE_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY);

    }

    @AfterMethod
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }

}
