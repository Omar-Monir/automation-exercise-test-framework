package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrandPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public BrandPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    @FindBy(css = "[href='/brand_products/Madame']")
    WebElement lnkMadame;

    public boolean UserInBrandPage(){

        return myFrameWork.getCurrentURL().contains(Utils.Constants.BRAND_PAGE_URL);

    }

    public void scrollToMadame(){

        myFrameWork.scrollToElement(lnkMadame);

    }

    public void clickOnMadame(){

        myFrameWork.scrollToElement(lnkMadame);
        myFrameWork.click(lnkMadame);

    }

}
