package pages;

import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by dinara.trifanova on 3/24/2017.
 */
public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSizeNeeded() {
        return (driver.findElements(By.name("options[Size]")).size() > 0);
    }

    public void fillSize() {
        Select select = new Select(driver.findElement(By.name("options[Size]")));
        select.selectByIndex(1);
    }

    public void addToCart() {
        driver.findElement(By.name("add_cart_product")).click();
    }

    public void waitUntilQuantityIsIncreased(int quantityBefore) {
        webDriverWait.until((WebDriver d) -> (Integer.parseInt(driver.findElement(By.className("quantity")).getAttribute("textContent"))) == quantityBefore + 1);
    }

}
