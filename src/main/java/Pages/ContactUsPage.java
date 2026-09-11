package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {

    WebDriver driver;
    SeleniumFrameWork myFrameWork;
    public ContactUsPage(WebDriver driver){

        this.driver=driver;
        myFrameWork= new SeleniumFrameWork(driver);
        PageFactory.initElements(driver,this);

    }

    @FindBy(css="div[class='contact-form'] h2")
    WebElement txtGetInTouch;

    @FindBy(xpath="//div[text()='Success! Your details have been submitted successfully.']")
    WebElement txtSuccessSubmit;

    @FindBy(name="name")
    WebElement name;

    @FindBy(name="email")
    WebElement email;

    @FindBy(name="subject")
    WebElement subject;

    @FindBy(name = "message")
    WebElement message;

    @FindBy(name = "upload_file")
    WebElement uploadFile;

    @FindBy(css = "[data-qa='submit-button']")
    WebElement btnSubmit;

    @FindBy(css = ".btn-success")
    WebElement btnContinue;

    public boolean verifyGetInTouchIsVisible(){

        return myFrameWork.getText(txtGetInTouch).contains(Utils.Constants.GET_IN_TOUCH_PAGE_GET_IN_TOUCH_MESSAGE);

    }

    public boolean verifySubmitSuccessfullyMessageIsVisible(){

        return myFrameWork.getText(txtSuccessSubmit).contains(Utils.Constants
                .GET_IN_TOUCH_PAGE_SUBMIT_SUCCESSFULLY_MESSAGE);

    }

    public void submit(){

        myFrameWork.sendKeys(name, Utils.Constants.FIRST_NAME);
        myFrameWork.sendKeys(email,Utils.Constants.MY_EMAIL);
        myFrameWork.sendKeys(subject,Utils.Constants.SUBJECT);
        myFrameWork.sendKeys(message,Utils.Constants.MESSAGE);
        myFrameWork.uploadFile(uploadFile,Utils.Constants.FILE_PATH);
        myFrameWork.click(btnSubmit);
        myFrameWork.acceptAlert();

    }

    public HomePage clickContinue(){

        myFrameWork.click(btnContinue);
        return new HomePage(driver);

    }





}
