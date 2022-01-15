import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HotelSearch {

    @Test
    public void searchHotel() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/");
        WebElement searchWindow = driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']"));
        searchWindow.click();
        WebElement searchInput = driver.findElement(By.xpath("//div[@id='select2-drop']//input"));
        searchInput.sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();

        WebElement checkInInput = driver.findElement(By.name("checkin"));
        checkInInput.sendKeys("25/01/2022");
        WebElement checkOutInput = driver.findElement(By.name("checkout"));
        checkOutInput.sendKeys("30/01/2022");
    }
}
