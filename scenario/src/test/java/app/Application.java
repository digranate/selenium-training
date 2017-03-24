package app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;

/**
 * Created by dinara.trifanova on 3/24/2017.
 */
public class Application {
    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public void quit() {
        driver.quit();
    }

    public Application() {
            driver = new ChromeDriver();
            mainPage = new MainPage(driver);
            productPage = new ProductPage(driver);
            cartPage = new CartPage(driver);
    }

    private void addProduct(int productNum) {
        mainPage.open();
        int valueBefore = mainPage.getQuantity();
        mainPage.openProduct(productNum);

        if (productPage.isSizeNeeded())
            productPage.fillSize();
        productPage.addToCart();
        productPage.waitUntilQuantityIsIncreased(valueBefore);
    }


    public void addAndRemoveProductsToCart(int productsCount) {
        for (int i = 0; i < productsCount; i++) {
            addProduct(i);
        }

        cartPage.open();
        while (!cartPage.isEmpty()) {
            int sizeBefore = cartPage.countProductsInCart();
            WebElement webElement = cartPage.getAndClickOnFirstShortcut();
            cartPage.removeProduct();
            cartPage.waitUntilProductIsRemovedFromTable(sizeBefore);
            cartPage.waitUntilProductIsRemovedFromScreenShots(webElement);
        }
        cartPage.removeProduct();
        cartPage.waitUntilTableDisappears();
    }
}
