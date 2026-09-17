package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public ProductDetailsPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    @FindBy(css = "div[class='product-information'] h2")
    WebElement productName;

    @FindBy(xpath = "//p[text()='Category: Women > Tops']")
    WebElement category;

    @FindBy(css = "div span span")
    WebElement price;

    @FindBy(xpath = "//b[text()='Availability:']")
    WebElement availability;

    @FindBy(xpath = "//b[text()='Condition:']")
    WebElement condition;

    @FindBy(xpath = "//b[text()='Brand:']")
    WebElement brand;

    @FindBy (id = "quantity")
    WebElement counterQuantity;

    @FindBy (css = "span button")
    WebElement btnAddToCart;

    @FindBy (css = "a u")
    WebElement lnkViewCart;

    @FindBy (css = "[href='#reviews']")
    WebElement txtWriteYourReview;

    @FindBy (id = "name")
    WebElement enterName;

    @FindBy (id = "email")
    WebElement enterEmail;

    @FindBy (id = "review")
    WebElement enterReview;

    @FindBy (id = "button-review")
    WebElement btnSubmitReview;

    @FindBy (css = "div[class='col-md-12 form-group'] div[class='alert-success alert']")
    WebElement reviewMessage;



    public boolean verifyThatProductDetailsPageIsVisibleSuccessfully(){

        return myFrameWork.getCurrentURL().contains(Utils.Constants.FIRST_PRODUCT_DETAILS_PAGE_URL);

    }

    public boolean verifyThatDetailIsVisible(){

        return myFrameWork.isElementVisible(productName)&& myFrameWork.isElementVisible(category)
                && myFrameWork.isElementVisible(price)&& myFrameWork.isElementVisible(availability)
                && myFrameWork.isElementVisible(condition) && myFrameWork.isElementVisible(brand);

    }

    public void setQuantity(){

            myFrameWork.sendKeys(counterQuantity,Utils.Constants.QUANTITY);

    }

    public void clickAddToCart(){

        myFrameWork.click(btnAddToCart);

    }

    public CartPage clickViewCart(){

        myFrameWork.click(lnkViewCart);
        return new CartPage(driver);

    }

    public boolean checkWriteYourReviewIsVisible(){

        return myFrameWork.getText(txtWriteYourReview).contains(Utils.Constants
                .WRITE_YOUR_REVIEW_TEXT);

    }

    public void clickSubmitReviewButton(){

        myFrameWork.scrollToElement(btnSubmitReview);
        myFrameWork.sendKeys(enterName,Utils.Constants.MY_EMAIL);
        myFrameWork.sendKeys(enterEmail,Utils.Constants.VALID_PASSWORD);
        myFrameWork.sendKeys(enterReview,Utils.Constants.REVIEW);
        myFrameWork.click(btnSubmitReview);

    }

    public boolean checkTheReviewMessageIsAppeared(){

        return myFrameWork.getText(reviewMessage).contains(Utils.Constants.REVIEW_MESSAGE);

    }

}
