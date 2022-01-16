package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;

public class SignUpPage {

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountList;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpList;

    @FindBy(name = "firstname")
    private WebElement firstname;

    @FindBy(name = "lastname")
    private WebElement lastname;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpassword;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpBtn;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void getSignUpPage() {
        myAccountList.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpList.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
    }

    public void setFirstname(String name) {
        firstname.sendKeys(name);
    }

    public void setLastname(String surname) {
        lastname.sendKeys(surname);
    }

    public void setPhoneNumber(String phoneNumber) {
        phone.sendKeys(phoneNumber);
    }

    public void setEmailAddress(String emailAddress) {
        email.sendKeys(emailAddress);
    }

    public void setPassword(String pass) {
        password.sendKeys(pass);
    }

    public void setConfirmPassword(String confirmPass) {
        confirmpassword.sendKeys(confirmPass);
    }

    public void signUp() {
        signUpBtn.click();
    }

    public void fillSignUpForm(String name, String surname, String phoneNumber, String emailAddress, String pass, String confirmPass) {
        firstname.sendKeys(name);
        lastname.sendKeys(surname);
        phone.sendKeys(phoneNumber);
        email.sendKeys(emailAddress);
        password.sendKeys(pass);
        confirmpassword.sendKeys(confirmPass);
        signUpBtn.click();
    }

    public void fillSignUpForm(User user) {
        firstname.sendKeys(user.getName());
        lastname.sendKeys(user.getSurname());
        phone.sendKeys(user.getPhone());
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPass());
        confirmpassword.sendKeys(user.getConfirmPass());
        signUpBtn.click();
    }
}
