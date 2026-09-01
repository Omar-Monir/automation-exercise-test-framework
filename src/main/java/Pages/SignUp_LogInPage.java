package Pages;

import MyFrameWork.SeleniumFrameWork;
import Utilites.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUp_LogInPage {

    public WebDriver driver;
    SeleniumFrameWork myFrameWork;

    public SignUp_LogInPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
        myFrameWork=new SeleniumFrameWork(driver);

    }

    @FindBy(css = "div[class='signup-form'] h2")
    WebElement txtNewUserSignup;

    @FindBy(xpath = "//h2[text()='Login to your account']")
    WebElement txtLoggingToYourAccount;

    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    WebElement txtEmailOrPasswordIsNotCorrect;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    WebElement txtEmailAddressAlreadyExist;

    @FindBy(css = "input[name='name']")
    WebElement name;

    @FindBy(css = "input[data-qa='signup-email']")
    WebElement signUpEmail;

    @FindBy(css = "input[data-qa='login-email']")
    WebElement logInEmail;

    @FindBy(css = "input[name='password'")
    WebElement password;

    @FindBy(css = "button[data-qa='signup-button']")
    WebElement btnSignUp;

    @FindBy(css = "button[data-qa='login-button']")
    WebElement btnLogIn;



    public boolean checkNewUserSignupMassageIsVisible(){

       return myFrameWork.getText(txtNewUserSignup).contains(Utils.Constants.SIGNUP_LOGIN_PAGE_NEW_USER_SIGNUP_MESSAGE);

    }

    public boolean checkLoggingToYourAccountMassageIsVisible(){

        return myFrameWork.getText(txtLoggingToYourAccount).contains(Utils.Constants.
                SIGNUP_LOGIN_PAGE_LOGIN_TO_YOUR_ACCOUNT_MESSAGE);

    }

    public boolean checkEmailAddressAlreadyExistMassageIsVisible(){

        return myFrameWork.getText(txtEmailAddressAlreadyExist).contains(Utils.Constants.
                SIGNUP_LOGIN_PAGE_EMAIL_ADDRESS_IS_EXIST_MESSAGE);

    }

    public EnterAccountInformation signUp(){

        myFrameWork.sendKeys(name, Utils.Constants.VALID_NAME);
        myFrameWork.sendKeys(signUpEmail,Utils.generateEmailWithTimeStamp());
        myFrameWork.click(btnSignUp);
        return new EnterAccountInformation(driver);

    }

    public EnterAccountInformation signUpWithMyEmail(){

        myFrameWork.sendKeys(name, Utils.Constants.VALID_NAME);
        myFrameWork.sendKeys(signUpEmail,Utils.Constants.MY_EMAIL);
        myFrameWork.click(btnSignUp);
        return new EnterAccountInformation(driver);

    }

    public HomePage logInWithValidEmailAndPassword(){

        myFrameWork.sendKeys(logInEmail,Utils.Constants.VALID_EMAIL);
        myFrameWork.sendKeys(password,Utils.Constants.VALID_PASSWORD);
        myFrameWork.click(btnLogIn);
        return new HomePage(driver);
    }

    public void logInWithInValidEmailAndPassword(){

        myFrameWork.sendKeys(logInEmail,Utils.Constants.INVALID_EMAIL);
        myFrameWork.sendKeys(password,Utils.Constants.INVALID_PASSWORD);
        myFrameWork.click(btnLogIn);

    }

    public boolean checkEmailOrPasswordIsNotCorrectMessageIsVisible(){

        return myFrameWork.getText(txtEmailOrPasswordIsNotCorrect).contains(Utils.Constants.
                SIGNUP_LOGIN_PAGE_YOUR_EMAIL_PASSWORD_IS_NOT_CORRECT_MESSAGE);

    }

    public HomePage logInWithMyEmailAndPassword(){

        myFrameWork.sendKeys(logInEmail,Utils.Constants.MY_EMAIL);
        myFrameWork.sendKeys(password,Utils.Constants.VALID_PASSWORD);
        myFrameWork.click(btnLogIn);
        return new HomePage(driver);

    }

    public boolean UserInSignUp_LogInPage(){

        return myFrameWork.getCurrentURL().contains(Utils.Constants.SIGNUP_LOGIN_URL);
    }




}
