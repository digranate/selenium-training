import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sammy on 08.03.2017.
 */
public class RegistrationTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 15);
    }


    @Test
    public void regisration() {
        //driver.get("http://localhost:8080/litecart/admin");
        driver.get("http://localhost:8080/litecart/en/create_account");
        driver.findElement(By.name("firstname")).sendKeys("Dinara");
        driver.findElement(By.name("lastname")).sendKeys("Trifanova");
        driver.findElement(By.name("address1")).sendKeys("Pushkin");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("SPb");

        String email = "dinara" + new Random().nextInt(100000) + "@kuku.ku";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+71234567");

        String password = "tratata";
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);

        Select select = new Select(driver.findElement(By.name("country_code")));
        select.selectByVisibleText("United States");

        driver.findElement(By.name("create_account")).click();

        driver.get("http://localhost:8080/litecart/en/logout");
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("login")).click();
        driver.get("http://localhost:8080/litecart/en/logout");
    }


    @After
    public void afterActions() {
        driver.quit();
    }

}
