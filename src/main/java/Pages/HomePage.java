package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    @FindBy(css = "a[href='/login']")
    WebElement lnkSignUp_LogIn;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    WebElement lnkLoggedInAsUserName;

    @FindBy(css = "a[href='/delete_account']")
    WebElement lnkDeleteAccount;

    @FindBy(css = "a[href='/logout']")
    WebElement lnkLogOutAccount;

    @FindBy(xpath = "//a[@href='/contact_us']")
    WebElement lnkContactUs;

    @FindBy (css = "li a[href='/test_cases']")
    WebElement lnkTestCases;

    @FindBy(css = "a[href='/products']")
    WebElement lnkProducts;

    @FindBy(css = "a[href='/view_cart']")
    WebElement lnkCart;

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

    @FindBy(css = "a[href='/product_details/4']")
    WebElement lnkFourthItemViewProduct;

    @FindBy(css = "img[src='/get_product_picture/1']")
    WebElement imgFirstProduct;

    @FindBy(css = "div[class='overlay-content'] a[data-product-id='1']")
    WebElement btnAddToCart;

    @FindBy(css = "a[href='/view_cart'] u")
    WebElement lnkViewCart;

    @FindBy(css = "[href='/product_details/1']")
    WebElement lnkFirstViewProduct;

    @FindBy(id = "accordian")
    WebElement categories;

    @FindBy(css = "a[href='#Women'] span[class='badge pull-right'] ")
    WebElement btnWomenPlus;

    @FindBy(css = "[href='/category_products/1']")
    WebElement lnkDress;

    public boolean verifyThatHomePageIsVisibleSuccessfully() {

        return myFrameWork.getCurrentURL().contains(Utils.Constants.BASE_URL);

    }

    public boolean checkLoggedInAsUserNameIsVisible() {

        return myFrameWork.getText(lnkLoggedInAsUserName).contains(Utils.Constants.HOME_PAGE_LOGGED_IN_AS_MESSAGE+
                Utils.Constants.VALID_NAME);

    }

    public boolean verifyHomeTextSubscription(){

        return myFrameWork.getText(txtSubscription).contains(Utils.Constants.HOME_PAGE_SUBSCRIPTION_MESSAGE);

    }

    public boolean verifyHomeSuccessfulSubscriptionText(){

        return myFrameWork.getText(txtSuccessSubscribe).contains(Utils.Constants
                .HOME_PAGE_SUCCESSFUL_SUBSCRIPTION_MESSAGE);

    }

    public SignUp_LogInPage clickSignUp_LogIn() {

        myFrameWork.click(lnkSignUp_LogIn);
        return new SignUp_LogInPage(driver);

    }

    public ProductsPage clickProducts() {

        myFrameWork.click(lnkProducts);
        return new ProductsPage(driver);

    }

    public ContactUsPage clickContactUs() {

        myFrameWork.click(lnkContactUs);
        return new ContactUsPage(driver);

    }

    public TestCasesPage clickTestCasesPage() {

        myFrameWork.click(lnkTestCases);
        return new TestCasesPage(driver);

    }

    public CartPage clickCartPage() {

        myFrameWork.click(lnkCart);
        return new CartPage(driver);

    }

    public void clickHomeSubscribe(){

        myFrameWork.sendKeys(subscribeEmail,Utils.Constants.MY_EMAIL);
        myFrameWork.click(btnSubscribe);

    }

    public void scrollToHomeFooter(){

        myFrameWork.scrollToElement(footer);

    }

    public void scrollToFirstProductView(){

        myFrameWork.scrollToElement(lnkFirstViewProduct);

    }



    public AccountDeletedPage clickDeleteAccount() {

        myFrameWork.click(lnkDeleteAccount);
        return new AccountDeletedPage(driver);

    }

    public SignUp_LogInPage clickLogOutAccount() {

        myFrameWork.click(lnkLogOutAccount);
        return new SignUp_LogInPage(driver);

    }

    public void addProductToCart(){

        myFrameWork.scrollToElement(lnkFourthItemViewProduct);
        myFrameWork.hoverOverElement(imgFirstProduct);
        myFrameWork.click(btnAddToCart);

    }

    public CartPage clickViewCart(){

        myFrameWork.click(lnkViewCart);
        return new CartPage(driver);

    }

    public boolean checkThatCategoriesAreVisible(){

        return myFrameWork.isElementVisible(categories);

    }

    public CategoryPage clickOnTheDress(){

        myFrameWork.click(btnWomenPlus);
        myFrameWork.click(lnkDress);
        return new CategoryPage(driver);

    }

}
