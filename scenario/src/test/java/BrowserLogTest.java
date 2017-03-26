import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by Sammy on 08.03.2017.
 */
public class BrowserLogTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        //WebDriver driver = new ChromeDriver(cap);
        driver = new ChromeDriver(cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 15);
    }


    @Test
    public void browserLog() throws Exception {
        driver.get("http://litecart.stqa.ru/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("0b7dba1c77df25bf0");
        driver.findElement(By.name("login")).click();

        File file = new File("logs.txt");
        if (!file.exists()) file.createNewFile();
        try (FileWriter fileWriter = new FileWriter(file)) {
            driver.get("http://litecart.stqa.ru/admin/?app=catalog&doc=catalog&category_id=1");
            List<WebElement> webElementList = driver.findElements(By.xpath("//tr[@class='row']/td[3][*[1][not (contains(@class,'folder'))]]"));
            int size = webElementList.size();
            for (int i = 0; i < size; i++) {
                driver.findElement(By.xpath("(//tr[@class='row']/td[3][*[1][not (contains(@class,'folder'))]])[" + (i + 1) + "]")).click();
                List<LogEntry> logEntries = driver.manage().logs().get("browser").getAll();
                for (LogEntry logEntry : logEntries) {
                    String s = "log for product " + (i + 1) + " " + logEntry.getMessage() +" Level is " + logEntry.getLevel();
                    System.out.println(s);
                    fileWriter.write(s + "\n");
                }
            }
            driver.get("http://litecart.stqa.ru/admin/?app=catalog&doc=catalog&category_id=1");
        }
    }


    @After
    public void afterActions() {
        driver.quit();
    }

}
