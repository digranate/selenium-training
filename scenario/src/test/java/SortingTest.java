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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dinara.trifanova on 2/21/2017.
 */
public class SortingTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 15);
    }

    @Test
    public void checkAllCountries() {
        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> webElements = driver.findElements
                (By.xpath("//tr[@class='row']//a[not(@title = 'Edit')]"));

        List<WebElement> listForSort = new ArrayList<>(webElements);
        listForSort.sort((o1,o2)->o1.getAttribute("textContent").compareTo(o2.getAttribute("textContent")));

        Assert.assertTrue(webElements.equals(listForSort));

    }

    @Test
    public void checkAllNonZeroZones() {
        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> webElements = driver.findElements
                (By.xpath("//tr[@class='row']"));

        for (int i = 0; i < webElements.size(); i++) {
            int zoneCount =
                    Integer.parseInt(driver.findElement(
                            By.xpath(
                                    "((//tr[@class='row'])[" + (i + 1) + "]/td)[last()-1]")).getAttribute("textContent"));

            if (zoneCount > 0) {
                List<String> zonesNames = new ArrayList<>();

                driver.findElement(By.xpath("(//tr[@class='row'])[" + (i + 1) + "]//a")).click();
                List<WebElement> webElements1 = driver.findElements(By.xpath("//*[@id='table-zones']//tr[not(@class = 'header')][td[1]/input]"));
                for (WebElement webElement : webElements1) {
                    zonesNames.add(webElement.findElement(By.xpath("(.//td)[last()-1]")).getAttribute("textContent"));
                }

                List<String> listForSort = new ArrayList<>(zonesNames);
                listForSort.sort((o1,o2)->o1.compareTo(o2));
                Assert.assertTrue(zonesNames.equals(listForSort));

                driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");

            }

        }

    }

    @Test
    public void checkGeoZones() {
        driver.get("http://localhost:8080/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> webElements = driver.findElements(By.xpath("//*[@id='content']//tr[@class='row']"));
        for (int i = 0; i < webElements.size(); i++) {
            driver.findElement(By.xpath("(//*[@id='content']//tr[@class='row'])[" + (i + 1) + "]//a")).click();

            List<WebElement> webElements1 = driver.findElements(By.xpath("//*[@id='table-zones']//tr[not(@class = 'header')][td/input[starts-with(@name,'zones')]]"));
            List<String> geoNames = new ArrayList<>();
            for (WebElement webElement: webElements1) {
            geoNames.add(webElement.findElement(By.xpath(".//td/select[contains(@name,'zone_code')]/option[@selected]")).getAttribute("textContent"));
            }

            List<String> listForSort = new ArrayList<>(geoNames);

            listForSort.sort((o1,o2)->o1.compareTo(o2));
            Assert.assertTrue(geoNames.equals(listForSort));

            driver.get("http://localhost:8080/litecart/admin/?app=geo_zones&doc=geo_zones");

        }

    }


    @After
    public void afterActions() {
        driver.quit();
    }
}
