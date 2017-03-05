import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sammy on 05.03.2017.
 */
public class ProperGoodsPageIETest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 15);
    }


    @Test
    public void checkMainAndConcreteGoodsPages() {
        driver.get("http://localhost:8080/litecart");

        List<WebElement> webElements = driver.findElements(By.xpath("//*[@id='box-campaigns']//li"));
        for (int i = 0; i < webElements.size(); i++) {
            WebElement webElement = driver.findElement(By.xpath("(//*[@id='box-campaigns']//li)[" + (i + 1) + "]"));
            String nameOnMainPage = webElement.findElement(By.xpath(".//div[@class='name']")).getAttribute("textContent");

            WebElement regularPrice = webElement.findElement(By.xpath(".//*[@class='regular-price']"));
            WebElement campaignPrice = webElement.findElement(By.xpath(".//*[@class='campaign-price']"));

            String regularPriceOnMainPage = regularPrice.getAttribute("textContent");
            String campaignPriceOnMainPage = campaignPrice.getAttribute("textContent");

            String regularPriceColor = regularPrice.getCssValue("color");
            String regularPriceFontSize = regularPrice.getCssValue("font-size");
            String regularPriceStrike = regularPrice.getCssValue("text-decoration");

            String campaignPriceColor = campaignPrice.getCssValue("color");
            String campaignPriceFontSize = campaignPrice.getCssValue("font-size");
            String campaignPriceFontWeight = campaignPrice.getCssValue("font-weight");

            BigDecimal regularFontZize = new BigDecimal(regularPriceFontSize.replace("px", ""));
            BigDecimal campaignFontZize = new BigDecimal(campaignPriceFontSize.replace("px", ""));

            Assert.assertTrue(regularFontZize.compareTo(campaignFontZize) < 0);
            Assert.assertEquals("rgba(119, 119, 119, 1)", regularPriceColor);
            Assert.assertEquals("line-through", regularPriceStrike);

            Assert.assertEquals("rgba(204, 0, 0, 1)", campaignPriceColor);
            Assert.assertEquals("900", campaignPriceFontWeight);

            driver.findElement(By.xpath("(//*[@id='box-campaigns']//li)[" + (i + 1) + "]//a")).click();


            String nameOnConcretePage = driver.findElement(By.xpath("//*[@id='box-product']//*[@class='title']")).getAttribute("textContent");

            regularPrice = driver.findElement(By.xpath("//*[@id='box-product']//*[@class='information']//*[@class='regular-price']"));
            campaignPrice = driver.findElement(By.xpath("//*[@id='box-product']//*[@class='information']//*[@class='campaign-price']"));


            String regularPriceOnConcretePage = regularPrice.getAttribute("textContent");
            String campaignPriceOnConcretePage = campaignPrice.getAttribute("textContent");

            regularPriceColor = regularPrice.getCssValue("color");
            regularPriceFontSize = regularPrice.getCssValue("font-size");
            regularPriceStrike = regularPrice.getCssValue("text-decoration");

            campaignPriceColor = campaignPrice.getCssValue("color");
            campaignPriceFontSize = campaignPrice.getCssValue("font-size");
            campaignPriceFontWeight = campaignPrice.getCssValue("font-weight");

            regularFontZize = new BigDecimal(regularPriceFontSize.replace("px", ""));
            campaignFontZize = new BigDecimal(campaignPriceFontSize.replace("px", ""));

            Assert.assertTrue(regularFontZize.compareTo(campaignFontZize) < 0);


            Assert.assertEquals("rgba(102, 102, 102, 1)", regularPriceColor);
            Assert.assertEquals("line-through", regularPriceStrike);

            Assert.assertEquals("rgba(204, 0, 0, 1)", campaignPriceColor);
            Assert.assertEquals("700", campaignPriceFontWeight);

            Assert.assertEquals("Name differ", nameOnConcretePage, nameOnMainPage);
            Assert.assertEquals("Regular price differ", regularPriceOnConcretePage, regularPriceOnMainPage);
            Assert.assertEquals("Campaign price differ", campaignPriceOnConcretePage, campaignPriceOnMainPage);

            driver.get("http://localhost:8080/litecart");
        }
    }


    @After
    public void afterActions() {
        driver.quit();
    }
}

