package Tests;

import BrowserManager.BrowserManager;
import MyFrameWork.SeleniumFrameWork;
import Pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/*
   Test Case 6: Contact Us Form

   Test Steps
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Contact Us' button
5. Verify 'GET IN TOUCH' is visible
6. Enter name, email, subject and message
7. Upload file
8. Click 'Submit' button
9. Click OK button
10. Verify success message 'Success! Your details have been submitted successfully.' is visible
11. Click 'Home' button and verify that landed to home page successfully
 */

public class TestCase6 extends BrowserManager {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    HomePage homePage;
    ContactUsPage contactUsPage;

    @BeforeTest
    public void startUp() throws IOException {

        loadFromPropertiesFile();
        driver = openBrowserAndURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    @Test
    public void testContactUsForm(){

        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully());
        contactUsPage=homePage.clickContactUs();
        Assert.assertTrue(contactUsPage.verifyGerInTouchIsVisible());
        contactUsPage.submit();
        Assert.assertTrue(contactUsPage.verifySubmitSuccessfullyMessageIsVisible());
        homePage=contactUsPage.clickContinue();
        Assert.assertTrue(homePage.verifyThatHomePageIsVisibleSuccessfully());

    }

    @AfterTest
    public void endOfTheTest() {

        myFrameWork.closeBrowser();

    }




}
