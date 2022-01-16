package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.pages.LoggedUserPage;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String name = "Andrzej";
        String lastname = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "tester" + randomNumber + "@test.pl";

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.getSignUpPage();
        signUpPage.fillSignUpForm(name, lastname, "333444555", email, "admin1234", "admin1234");

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
        User user = new User();
        user.setName("Andrzej");
        user.setSurname("Testowy");
        user.setPhone("111222333");
        user.setEmail("test.com");
        user.setPass("admin123");
        user.setConfirmPass("admin123");
        signUpPage.fillSignUpForm(user);

        LoggedUserPage resultPage = new LoggedUserPage(driver);
        Assert.assertTrue(resultPage.alertsList().contains("The Email field must contain a valid email address."));
    }
}
