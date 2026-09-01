package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilites.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public CartPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    @FindBy(css = ".footer-bottom")
    WebElement footer;

    @FindBy(id = "susbscribe_email")
    WebElement subscribeEmail;

    @FindBy(id = "subscribe")
    WebElement btnSubscribe;

    @FindBy(xpath = "//h2[text()='Subscription']")
    WebElement txtSubscription;

    @FindBy(id = "success-subscribe")
    WebElement txtSuccessSubscribe;

    @FindBy(id = "product-1")
    WebElement lstFirstProduct;

    @FindBy(id = "product-2")
    WebElement lstSecondProduct;

    @FindBy(css = "td button")
    WebElement quantityOfTheProduct;

    public void scrollToCartFooter(){

        myFrameWork.scrollToElement(footer);

    }

    public void clickCartSubscribe(){

        myFrameWork.sendKeys(subscribeEmail, Utils.Constants.MY_EMAIL);
        myFrameWork.click(btnSubscribe);

    }

    public boolean verifyCartTextSubscription(){

        return myFrameWork.getText(txtSubscription).contains(Utils.Constants.CART_PAGE_SUBSCRIPTION_MESSAGE);

    }

    public boolean verifyCartSuccessfulSubscriptionText(){

        return myFrameWork.getText(txtSuccessSubscribe).contains(Utils.Constants
                .CART_PAGE_SUCCESSFUL_SUBSCRIPTION_MESSAGE);

    }

    public boolean checkFirstProductIsAdded(){

        return myFrameWork.isElementVisible(lstFirstProduct);

    }

    public boolean checkSecondProductIsAdded(){

        return myFrameWork.isElementVisible(lstSecondProduct);

    }

    public boolean checkTheQuantityOfTheProduct(){

        return  myFrameWork.getText(quantityOfTheProduct).contains(Utils.Constants.QUANTITY);

    }

}
