package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String name = "Andrzej";
        String lastname = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "tester" + randomNumber + "@test.pl";

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.getSignUpPage();
        signUpPage.setFirstname(name);
        signUpPage.setLastname(lastname);
        signUpPage.setPhoneNumber("333444555");
        signUpPage.setEmailAddress(email);
        signUpPage.setPassword("admin1234");
        signUpPage.setConfirmPassword("admin1234");
        signUpPage.signUp();

        LoggedUserPage resultPage = new LoggedUserPage(driver);
        Assert.assertEquals(resultPage.getWelcomeTest(), "Hi, " + name + " " + lastname);
    }

    @Test
    public void signUpWithoutUserDataTest() {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.getSignUpPage();
        signUpPage.setFirstname("Andrzej");
        signUpPage.signUp();

        LoggedUserPage resultPage = new LoggedUserPage(driver);
        Assert.assertTrue(resultPage.alertsList().contains("The Email field is required."));
        Assert.assertTrue(resultPage.alertsList().contains("The Password field is required."));
        Assert.assertTrue(resultPage.alertsList().contains("The Password field is required."));
        Assert.assertTrue(resultPage.alertsList().contains("The Last Name field is required."));
    }

    @Test
    public void signUpWithInvalidEmailTest() {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.getSignUpPage();
        signUpPage.setFirstname("Andrzej");
        signUpPage.setLastname("Testowy");
        signUpPage.setPhoneNumber("111222333");
        signUpPage.setEmailAddress("test.com");
        signUpPage.setPassword("admin123");
        signUpPage.setConfirmPassword("admin123");
        signUpPage.signUp();

        LoggedUserPage resultPage = new LoggedUserPage(driver);
        Assert.assertTrue(resultPage.alertsList().contains("The Email field must contain a valid email address."));
    }
}
