package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilites.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public AccountCreatedPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
        myFrameWork=new SeleniumFrameWork(driver);

    }

    @FindBy(css ="h2 b")
    WebElement txtAccountCreated;

    @FindBy (css = ".btn-primary")
    WebElement btnContinue;

    public boolean checkAccountCreatedMassageIsVisible(){

        return myFrameWork.getText(txtAccountCreated).contains(Utils.Constants
                .ACCOUNT_CREATED_PAGE_ACCOUNT_CREATED_MESSAGE);

    }

    public HomePage clickContinueButton(){

        myFrameWork.click(btnContinue);
        return new HomePage(driver);

    }
}
