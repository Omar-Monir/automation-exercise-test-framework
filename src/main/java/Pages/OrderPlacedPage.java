package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
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

    @FindBy(css = "a[href*='download_invoice']")
    WebElement btnDownloadInvoice;

    @FindBy(css = "[class='pull-right'] a[href='/']")
    WebElement btnContinue;

    public AccountDeletedPage clickDeleteAccount(){

        myFrameWork.click(lnkDeleteAccount);
        return new AccountDeletedPage(driver);

    }

    public void clickDownloadInvoice() {
        myFrameWork.click(btnDownloadInvoice);
    }

    public HomePage clickContinue() {
        myFrameWork.click(btnContinue);
        return new HomePage(driver);
    }

    public boolean isInvoiceFileDownloaded() throws InterruptedException {

        return Utils.isFileDownloaded();

    }


}
