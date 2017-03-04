import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by dinara.trifanova on 2/21/2017.
 */


public class LoginTestFireFox {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        ///////////////////
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("c:\\Program Files (x86)\\Nightly\\firefox.exe");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        driver = new FirefoxDriver(caps);
        ////OR
        // FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files (x86)\\Nightly\\firefox.exe"));
        // driver = new FirefoxDriver(bin, new FirefoxProfile());

        tlDriver.set(driver);
        wait = new WebDriverWait(driver, 10);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driver.quit(); driver = null; }));
    }

    @Test
    public void myThirdTest()throws Exception {
        tlDriver.get().get("http://localhost:8080/litecart/admin");
        tlDriver.get().findElement(By.name("username")).sendKeys("admin");
        tlDriver.get().findElement(By.name("password")).sendKeys("admin");
        tlDriver.get().findElement(By.name("remember_me"));
        tlDriver.get().findElement(By.name("login")).click();
        Thread.sleep(10000);

    }
    @Test
    public void myFirstTest()throws Exception {
        tlDriver.get().get("http://localhost:8080/litecart/admin");

        Thread.sleep(10000);

    }


}
