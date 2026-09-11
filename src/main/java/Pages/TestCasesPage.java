package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TestCasesPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public TestCasesPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    public boolean verifyInTestCasesPage(){

        return myFrameWork.getCurrentURL().contains(Utils.Constants.TEST_CASES_PAGE_URL);

    }





}
