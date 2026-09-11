package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public ProductsPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    @FindBy(css = "[href='/brand_products/Biba']")
    WebElement lnkBiba;

    @FindBy(css = "[class='brands_products']")
    WebElement brand;

    @FindBy(css = "[href='/product_details/1']")
    WebElement lnkViewFirstProduct;

    @FindBy(css = "[href='/product_details/2']")
    WebElement lnkViewSecondProduct;

    @FindBy(css = "[href='/product_details/4']")
    WebElement lnkView4thProduct;

    @FindBy(css = "a[href='/view_cart'] u")
    WebElement lnkViewCart;

    @FindBy(id = "search_product")
    WebElement inpSearchInput;

    @FindBy(id = "submit_search")
    WebElement btnSearchSubmit;

    @FindBy(css = "h2[class]")
    WebElement txtSearchedProducts;

    @FindBy(css = " div[class='overlay-content'] a[data-product-id='1']")
    WebElement btnFirstAddToCart;

    @FindBy(css = " div[class='overlay-content'] a[data-product-id='2']")
    WebElement btnSecondAddToCart;

    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    WebElement btnContinue;

    @FindBy (css = "img[src='/get_product_picture/1']")
    WebElement imgFirstProduct;

    @FindBy (css = "img[src='/get_product_picture/2']")
    WebElement imgSecondProduct;


    public boolean verifyThatAllProductsPageIsVisibleSuccessfully(){

        return myFrameWork.getCurrentURL().contains(Utils.Constants.ALL_PRODUCTS_PAGE_URL);

    }

    public boolean verifyThatSearchedProductsMessageIsVisibleSuccessfully(){

        return myFrameWork.getText(txtSearchedProducts).contains(Utils.Constants
                .PRODUCT_PAGE_SEARCHED_PRODUCTS_MESSAGE);

    }

    public ProductDetailsPage clickViewFirstProduct(){

        myFrameWork.scrollToElement(lnkView4thProduct);
        myFrameWork.click(lnkViewFirstProduct);
        return new ProductDetailsPage(driver);

    }

    public void scrollTo4thProduct(){

        myFrameWork.scrollToElement(lnkView4thProduct);

    }

    public void searchForProduct(){

        myFrameWork.sendKeys(inpSearchInput,Utils.Constants.SEARCH_PRODUCT);
        myFrameWork.click(btnSearchSubmit);

    }

    public void clickAddFirstProductToCart(){

        myFrameWork.hoverOverElement(imgFirstProduct);
        myFrameWork.click(btnFirstAddToCart);


    }

    public void clickAddSecondProductToCart(){

        myFrameWork.hoverOverElement(imgSecondProduct);
        myFrameWork.click(btnSecondAddToCart);


    }

    public CartPage clickViewCart(){

        myFrameWork.click(lnkViewCart);
        return new CartPage(driver);

    }

    public void clickContinue(){

        myFrameWork.click(btnContinue);

    }

    public void scrollToBiba(){

        myFrameWork.scrollToElement(lnkBiba);

    }

    public boolean checkBrandIsVisible(){

        return myFrameWork.isElementVisible(brand);

    }

    public BrandPage clickOnBiba(){

        myFrameWork.click(lnkBiba);
        return new BrandPage(driver);

    }




}
