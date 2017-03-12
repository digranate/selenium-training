import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sammy on 08.03.2017.
 */
public class AddToCartTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 25);
    }


    @Test
    public void addProducts() {

        for (int i = 0; i < 3; i++) {
            driver.get("http://localhost:8080/litecart");

            int valueBefore = Integer.parseInt(driver.findElement(By.className("quantity")).getAttribute("textContent"));

            driver.findElements(By.xpath("//ul[@class='listing-wrapper products']/li")).get(i).click();
            if (driver.findElements(By.name("options[Size]")).size() > 0) {
                Select select = new Select(driver.findElement(By.name("options[Size]")));
                select.selectByIndex(1);
            }
            driver.findElement(By.name("add_cart_product")).click();
            webDriverWait.until((WebDriver d) -> (Integer.parseInt(driver.findElement(By.className("quantity")).getAttribute("textContent"))) == valueBefore + 1);
        }

        driver.get("http://localhost:8080/litecart/en/checkout");


        while (driver.findElements(By.xpath("//ul[@class='shortcuts']/li")).size()>0){
            int sizeBefore = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr")).size();

            driver.findElement(By.xpath("(//ul[@class='shortcuts']/li)[1]")).click();

            WebElement webElement = driver.findElement(By.xpath("(//ul[@class='shortcuts']/li)[1]"));
            driver.findElement(By.name("remove_cart_item")).click();
            webDriverWait.until((WebDriver d) -> (driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr")).size() == sizeBefore - 1));
            webDriverWait.until(ExpectedConditions.stalenessOf(webElement));
        }

        driver.findElement(By.name("remove_cart_item")).click();
        webDriverWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']"))));

    }


    @After
    public void afterActions() {
        driver.quit();
    }

}
