package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dinara.trifanova on 3/24/2017.
 */
public class Page {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public Page(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 10);
    }
}
