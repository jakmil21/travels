import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest {

    @Test
    public void signUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");

        String name = "Anrzej";
        String lastname = "Testowy";
        int randomNumber = (int) (Math.random()*1000);
        String email = "tester" + randomNumber + "@test.pl";
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElement(By.name("firstname")).sendKeys(name);
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("phone")).sendKeys("111222333");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("test1!");
        driver.findElement(By.name("confirmpassword")).sendKeys("test1!");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        WebElement welcomeText = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertEquals(welcomeText.getText(), "Hi, " + name + " " + lastname);
    }
}
