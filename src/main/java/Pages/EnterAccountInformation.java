package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilites.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Year;

public class EnterAccountInformation {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public EnterAccountInformation(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
        myFrameWork=new SeleniumFrameWork(driver);

        }

    @FindBy(id = "id_gender1")
    WebElement idMr;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="days")
    WebElement day;

    @FindBy(id="months")
    WebElement month;

    @FindBy(id="years")
    WebElement year;

    @FindBy(id="newsletter")
    WebElement chkNewsletter;

    @FindBy(id="optin")
    WebElement chkOffers;

    @FindBy(id="first_name")
    WebElement firstName;

    @FindBy(id="last_name")
    WebElement lastName;

    @FindBy(id="company")
    WebElement company;

    @FindBy(id="address1")
    WebElement address1;

    @FindBy(id="address2")
    WebElement address2;

    @FindBy(id="country")
    WebElement country;

    @FindBy(id="state")
    WebElement state;

    @FindBy(id="city")
    WebElement city;

    @FindBy(id="zipcode")
    WebElement zipcode;

    @FindBy(id="mobile_number")
    WebElement mobileNumber;

    @FindBy(css = "button[data-qa=\"create-account\"]")
    WebElement btnCreateAccount;

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    WebElement txtEnterAccountInformation;

    public AccountCreatedPage createAccount(){

        myFrameWork.click(idMr);
        myFrameWork.sendKeys(password, Utils.Constants.VALID_PASSWORD);
        myFrameWork.selectDropdownByValue(day,Utils.Constants.DAY);
        myFrameWork.selectDropdownByValue(month,Utils.Constants.MONTH);
        myFrameWork.selectDropdownByValue(year,Utils.Constants.YEAR);
        myFrameWork.checkCheckbox(chkNewsletter);
        myFrameWork.checkCheckbox(chkOffers);
        myFrameWork.sendKeys(firstName, Utils.Constants.FIRST_NAME);
        myFrameWork.sendKeys(lastName, Utils.Constants.LAST_NAME);
        myFrameWork.sendKeys(company, Utils.Constants.COMPANY);
        myFrameWork.sendKeys(address1, Utils.Constants.ADDRESS1);
        myFrameWork.sendKeys(address2, Utils.Constants.ADDRESS2);
        myFrameWork.selectDropdownByValue(country,Utils.Constants.COUNTRY);
        myFrameWork.sendKeys(state, Utils.Constants.STATE);
        myFrameWork.sendKeys(city, Utils.Constants.CITY);
        myFrameWork.sendKeys(zipcode, Utils.Constants.ZIPCODE);
        myFrameWork.sendKeys(mobileNumber, Utils.Constants.MOBILE_NUMBER);
        myFrameWork.click(btnCreateAccount);
        return new AccountCreatedPage(driver);

    }

    public boolean checkEnterAccountInformationMassageIsVisible(){

        return myFrameWork.getText(txtEnterAccountInformation).contains(Utils.Constants
                .ENTER_ACCOUNT_INFORMATION_PAGE_ENTER_ACCOUNT_INFORMATION);

    }






}
