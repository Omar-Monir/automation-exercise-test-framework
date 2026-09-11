package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public PaymentPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
        myFrameWork=new SeleniumFrameWork(driver);

    }

    @FindBy(css = "[name='name_on_card']")
    WebElement nameOnTheCard;

    @FindBy(css = "[name='card_number']")
    WebElement cardNumber;

    @FindBy(css = "[name='cvc']")
    WebElement cvc;

    @FindBy(css = "[name='expiry_month']")
    WebElement expirationMonth;

    @FindBy(css = "[name='expiry_year']")
    WebElement expirationYear;

    @FindBy(id = "submit")
    WebElement btnPayAndConfirmOrder;

    @FindBy(css = "div[id='success_message'] div[class='alert-success alert']")
    WebElement msgOrderPlacedSuccessfully;

    public OrderPlacedPage enterPaymentDetailsAndClickConfirmOrder(){

        myFrameWork.sendKeys(nameOnTheCard, Utils.Constants.NAME_ON_CARD);
        myFrameWork.sendKeys(cardNumber,Utils.Constants.CARD_NUMBER);
        myFrameWork.sendKeys(cvc,Utils.Constants.CVC);
        myFrameWork.sendKeys(expirationMonth,Utils.Constants.EXPIRATION_MONTH);
        myFrameWork.sendKeys(expirationYear,Utils.Constants.EXPIRATION_YEAR);
        myFrameWork.click(btnPayAndConfirmOrder);
        return new OrderPlacedPage(driver);

    }

    public boolean checkOrderPlacedSuccessfullyMessage(){

        return myFrameWork.getText(msgOrderPlacedSuccessfully).contains(Utils.Constants
                .ORDER_PLACED_SUCCESSFULLY_MASSAGE);

    }

}
