package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public CheckOutPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
        myFrameWork=new SeleniumFrameWork(driver);

    }

    @FindBy(css = "[name='message']")
    WebElement txtAddComment;

    @FindBy(css = "div[class='footer-widget']")
    WebElement footer;

    @FindBy(css = "a[href='/payment']")
    WebElement btnPlaceOrder;

    @FindBy(id = "address_delivery")
    WebElement deliveryAddress;

    @FindBy(id = "address_invoice")
    WebElement billingAddress;

    @FindBy(css = "a[href='/delete_account']")
    WebElement lnkDeleteAccount;

    public void goToCheckOutFooter(){

        myFrameWork.scrollToElement(footer);

    }

    public void addComment(){

        myFrameWork.sendKeys(txtAddComment,Utils.Constants.CHECK_OUT_COMMENT);

    }

    public PaymentPage clickPlaceOrder(){

        myFrameWork.click(btnPlaceOrder);
        return new PaymentPage(driver);

    }

    public boolean  checkTheDeliveryAddress(){

        return myFrameWork.getText(deliveryAddress).contains(Utils.Constants.ADDRESS1)&&
                myFrameWork.getText(deliveryAddress).contains(Utils.Constants.ADDRESS2);

    }

    public boolean  checkTheBillingAddress(){

        return myFrameWork.getText(billingAddress).contains(Utils.Constants.ADDRESS1)&&
                myFrameWork.getText(billingAddress).contains(Utils.Constants.ADDRESS2);

    }

    public AccountDeletedPage clickDeleteAccount() {

        myFrameWork.click(lnkDeleteAccount);
        return new AccountDeletedPage(driver);

    }


}
