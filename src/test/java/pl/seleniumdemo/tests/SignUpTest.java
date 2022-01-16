package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.pages.LoggedUserPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String name = "Andrzej";
        String lastname = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "tester" + randomNumber + "@test.pl";

        LoggedUserPage loggedUserPage = new SignUpPage(driver)
                .getSignUpPage()
                .setFirstname(name)
                .setLastname(lastname)
                .setPhoneNumber("444555666")
                .setEmailAddress(email)
                .setPassword("admin12345")
                .setConfirmPassword("admin12345")
                .signUp();

        LoggedUserPage resultPage = new LoggedUserPage(driver);
        Assert.assertEquals(resultPage.getWelcomeText(), "Hi, " + name + " " + lastname);
    }

    @Test
    public void signUpWithoutUserDataTest() {
        SignUpPage signUpPage = new SignUpPage(driver).getSignUpPage();
        signUpPage.signUp();

        List<String> errors = signUpPage.alertsList();

        Assert.assertTrue(errors.contains("The Email field is required."));
        Assert.assertTrue(errors.contains("The Password field is required."));
        Assert.assertTrue(errors.contains("The Password field is required."));
        Assert.assertTrue(errors.contains("The Last Name field is required."));
    }

    @Test
    public void signUpWithInvalidEmailTest() {
        SignUpPage signUpPage = new SignUpPage(driver)
                .getSignUpPage()
                .setFirstname("axc")
                .setLastname("asdf")
                .setPhoneNumber("444555666")
                .setEmailAddress("email")
                .setPassword("admin12345")
                .setConfirmPassword("admin12345");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.alertsList().contains("The Email field must contain a valid email address."));
    }
}
