import com.sun.jna.platform.win32.Sspi;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dinara.trifanova on 2/21/2017.
 */
public class LiteCartLoginAndFirstCheckTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 15);
    }


    @Test
    public void login() {
        //driver.get("http://localhost:8080/litecart/admin");
        driver.get("http://litecart.stqa.ru/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
        driver.findElement(By.name("login")).click();
    }


    @Test
    public void allReferencesClick() {
        //driver.get("http://localhost:8080/litecart/admin");
        driver.get("http://litecart.stqa.ru/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
        driver.findElement(By.name("login")).click();

        int sizeOuterList = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).size();
        for (int i = 0; i < sizeOuterList; i++) {
            driver.findElement(By.xpath("(//ul[@id='box-apps-menu']/li)[" + (i + 1) + "]")).click();
            WebElement webElement = driver.findElement(By.xpath("(//ul[@id='box-apps-menu']/li)[" + (i + 1) + "]"));
            int sizeInnerList = webElement.findElements(By.xpath(".//li")).size();
            for (int j = 0; j < sizeInnerList; j++) {
                driver.findElement(By.xpath("(//ul[@id='box-apps-menu']/li)[" + (i + 1) + "]")).findElement(By.xpath("(.//li)[" + (j + 1) + "]")).click();
                driver.findElement(By.cssSelector("#content h1"));
            }
        }
    }

    @Test
    public void checkAllStickers() {
        //driver.get("http://localhost:8080/litecart/admin");
        driver.get("http://litecart.stqa.ru");
        List<WebElement> webElements = driver.findElements
                (By.xpath("//*[starts-with(@class, 'product column')]"
                ));
        for (WebElement webElement : webElements) {
            Assert.assertTrue(webElement.findElements(By.xpath(".//div[starts-with(@class,'sticker')]")).size() == 1);
        }
    }




    @After
    public void afterActions() {
        driver.quit();
    }
}
