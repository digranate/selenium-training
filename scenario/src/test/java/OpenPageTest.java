import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dinara.trifanova on 2/21/2017.
 */
public class OpenPageTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
      driver = new ChromeDriver();
      webDriverWait = new WebDriverWait(driver,10);
    }

    @Test
    public void openPage() {
        driver.get("http://yandex.ru");
    }

    @After
    public void afterActions() {
        driver.quit();
    }
}
