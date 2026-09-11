package Pages;

import MyFrameWork.SeleniumFrameWork;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPlacedPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public OrderPlacedPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        myFrameWork = new SeleniumFrameWork(driver);

    }
    @FindBy(css = "a[href='/delete_account']")
    WebElement lnkDeleteAccount;

    public AccountDeletedPage clickDeleteAccount(){

        myFrameWork.click(lnkDeleteAccount);
        return new AccountDeletedPage(driver);

    }


}
