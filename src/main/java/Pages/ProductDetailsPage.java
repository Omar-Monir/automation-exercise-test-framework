package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilites.Utils;
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





}
