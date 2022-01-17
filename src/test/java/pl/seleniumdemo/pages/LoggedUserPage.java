package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LoggedUserPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement welcomeText;

    @FindBy(xpath = "//div[@class='alert alert-danger']/p")
    private List<WebElement> alertsList;

    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getWelcomeTest() {
        return welcomeText.getText();
    }

    public List<String> alertsList() {
        return alertsList.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
