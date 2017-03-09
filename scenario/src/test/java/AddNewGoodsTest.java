import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sammy on 08.03.2017.
 */
public class AddNewGoodsTest {
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
        driver.get("http://localhost:8080/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();


        driver.findElements(By.cssSelector("#box-apps-menu > li")).get(1).click();
        driver.findElements(By.cssSelector("#content > div > a")).get(1).click();
        driver.findElement(By.xpath("//*[@name='status'][@value='1']")).click();

        driver.findElement(By.name("name[en]")).sendKeys("ABC duck");
        driver.findElement(By.name("code")).sendKeys("ABC");

        driver.findElements(By.name("categories[]")).get(1).click();
        for (WebElement webElement : driver.findElements(By.name("product_groups[]"))) {
            webElement.click();
        }
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("500");
        driver.findElement(By.name("date_valid_from")).sendKeys("01.01.2017");
        driver.findElement(By.name("date_valid_to")).sendKeys("01.01.2999");

        File file = new File("src"+File.separator+"test"+File.separator+ "resources"+File.separator+"DuckABC.jpg");
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());


        driver.findElements(By.xpath("//*[@class='index']/li")).get(1).click();

        Select select = new Select(driver.findElement(By.name("manufacturer_id")));
        select.selectByIndex(1);
        driver.findElement(By.name("keywords")).sendKeys("ABC");
        driver.findElement(By.name("short_description[en]")).sendKeys("ABC duck");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("ABC duck is great");
        driver.findElement(By.name("head_title[en]")).sendKeys("ABC duck");
        driver.findElement(By.name("meta_description[en]")).sendKeys("ABC duck");

        driver.findElements(By.xpath("//*[@class='index']/li")).get(3).click();
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("10");

        select = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        select.selectByValue("USD");
        driver.findElement(By.name("gross_prices[USD]")).clear();
        driver.findElement(By.name("gross_prices[USD]")).sendKeys("12");
        driver.findElement(By.name("gross_prices[EUR]")).clear();
        driver.findElement(By.name("gross_prices[EUR]")).sendKeys("9");

        driver.findElement(By.name("save")).click();

    }


    @After
    public void afterActions() {
        driver.quit();
    }

}
