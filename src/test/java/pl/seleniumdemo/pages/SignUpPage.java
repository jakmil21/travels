package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

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

    @FindBy(xpath = "//div[@class='alert alert-danger']/p")
    private List<WebElement> alertsList;

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public SignUpPage getSignUpPage() {
        myAccountList.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpList.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        return this;
    }

    public SignUpPage setFirstname(String name) {
        firstname.sendKeys(name);
        return this;
    }

    public SignUpPage setLastname(String surname) {
        lastname.sendKeys(surname);
        return this;
    }

    public SignUpPage setPhoneNumber(String phoneNumber) {
        phone.sendKeys(phoneNumber);
        return this;
    }

    public SignUpPage setEmailAddress(String emailAddress) {
        email.sendKeys(emailAddress);
        return this;
    }

    public SignUpPage setPassword(String pass) {
        password.sendKeys(pass);
        return this;
    }

    public SignUpPage setConfirmPassword(String confirmPass) {
        confirmpassword.sendKeys(confirmPass);
        return this;
    }

    public LoggedUserPage signUp() {
        signUpBtn.click();
        return new LoggedUserPage(driver);
    }

    public List<String> alertsList() {
        return alertsList.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
