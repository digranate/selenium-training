import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by dinara.trifanova on 2/21/2017.
 */
public class LoginTestChrome {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 10);
    }

    @Test
    public void login() {
        driver.get("http://localhost:8080/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me"));
        driver.findElement(By.name("login")).click();

        WebElement webElement = driver.findElement(By.id("box-apps-menu"));
            webElement.findElement(By.xpath("//@id=app-")).click();

    }

    @After
    public void afterActions() {
        driver.quit();
    }
}
