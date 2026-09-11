package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public CategoryPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        myFrameWork = new SeleniumFrameWork(driver);

    }

    @FindBy(css = "[href='/category_products/3']")
    WebElement lnkTShirts;

    @FindBy(css = "a[href='#Men'] span[class='badge pull-right'] ")
    WebElement btnMenPlus;

    public boolean checkUserInCategoryPage(){

        return myFrameWork.getCurrentURL().contains(Utils.Constants.CATEGORY_PAGE_URL);

    }

    public void clickOnTShirts(){

        myFrameWork.click(btnMenPlus);
        myFrameWork.click(lnkTShirts);

    }

}
