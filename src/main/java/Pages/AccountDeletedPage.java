package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDeletedPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public AccountDeletedPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
        myFrameWork=new SeleniumFrameWork(driver);

    }

    @FindBy(xpath = "//b")
    WebElement txtDeleteAccountMassage;

    @FindBy(css = ".btn-primary")
    WebElement btnContinue;

    public boolean checkDeleteAccountMassageIsVisible(){

        return myFrameWork.getText(txtDeleteAccountMassage).contains(Utils.Constants
                .ACCOUNT_DELETED_PAGE_ACCOUNT_DELETED_MESSAGE);

    }

    public HomePage clickContinueButton(){

        myFrameWork.click(btnContinue);
        return new HomePage(driver);

    }

}
