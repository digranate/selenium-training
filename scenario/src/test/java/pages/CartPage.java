package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by dinara.trifanova on 3/24/2017.
 */
public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://litecart.stqa.ru/index.php/en/checkout");
    }

    public boolean isShortcutsEmpty() {
        return (driver.findElements(By.xpath("//ul[@class='shortcuts']/li")).size() == 0);
    }

    public int countProductsInCart() {
        return driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr")).size();
    }

    public WebElement getAndClickOnFirstShortcut(){
     return getAndClickOnShortcut(0);
    }

    private WebElement getAndClickOnShortcut(int productNum) {
        driver.findElement(By.xpath("(//ul[@class='shortcuts']/li)[" + (productNum + 1) + "]")).click();
        return driver.findElement(By.xpath("(//ul[@class='shortcuts']/li)[" + (productNum + 1) + "]"));
    }

    public void removeProduct() {
        driver.findElement(By.name("remove_cart_item")).click();
    }

    public void waitUntilProductIsRemovedFromTable(int sizeBefore) {
        webDriverWait.until((WebDriver d) -> (driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr")).size() == sizeBefore - 1));
    }

    public void waitUntilProductIsRemovedFromScreenShots(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.stalenessOf(webElement));
    }

    public void waitUntilTableDisappears() {
        webDriverWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']"))));
    }
}
