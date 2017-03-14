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
import sun.security.util.PropertyExpander;

import java.io.File;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sammy on 08.03.2017.
 */
public class NewWindowsOnEditTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 15);
    }


    @Test
    public void newWindowsOpenTest() {
        driver.get("http://litecart.stqa.ru/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
        driver.findElement(By.name("login")).click();
        driver.get("http://litecart.stqa.ru/admin/?app=countries&doc=countries");

        driver.findElement(By.xpath("//*[@class='row']//*[@title='Edit']")).click();
        int size = driver.findElements(By.xpath("//*[@class='fa fa-external-link']")).size();
        for (int i = 0; i < size; i++) {
            Set<String> windowHandlesOld = driver.getWindowHandles();
            String mainWindow = driver.getWindowHandle();
            driver.findElement(By.xpath("(//*[@class='fa fa-external-link'])[" + (i + 1) + "]")).click();
            webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windowHandlesNew = driver.getWindowHandles();
            windowHandlesNew.removeAll(windowHandlesOld);
            String newWindowHandle = windowHandlesNew.iterator().next();
            driver.switchTo().window(newWindowHandle);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }


    @After
    public void afterActions() {
        driver.quit();
    }

}
