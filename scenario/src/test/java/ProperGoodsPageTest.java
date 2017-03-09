import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dinara.trifanova on 3/6/2017.
 */
public class ProperGoodsPageTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void beforeActions() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 15);
    }


    @Test
    public void checkMainAndConcreteGoodsPages() {
        //driver.get("http://localhost:8080/litecart");
        driver.get("http://litecart.stqa.ru");

        List<WebElement> webElements = driver.findElements(By.xpath("//*[@id='box-campaigns']//li"));
        for (int i = 0; i < webElements.size(); i++) {
            WebElement webElement = driver.findElement(By.xpath("(//*[@id='box-campaigns']//li)[" + (i + 1) + "]"));
            String nameOnMainPage = webElement.findElement(By.xpath(".//div[@class='name']")).getAttribute("textContent");

            WebElement regularPrice = webElement.findElement(By.xpath(".//*[@class='regular-price']"));
            WebElement campaignPrice = webElement.findElement(By.xpath(".//*[@class='campaign-price']"));

            String regularPriceOnMainPage = regularPrice.getAttribute("textContent");
            String campaignPriceOnMainPage = campaignPrice.getAttribute("textContent");

            String regularPriceColor = regularPrice.getCssValue("color");
            String regularUnifiedColor = regularPriceColor.replaceAll("rgb[a]+\\(","").replaceAll(", [0-9]*\\)","");
            String regularPriceFontSize = regularPrice.getCssValue("font-size");
            String regularPriceStrike = regularPrice.getCssValue("text-decoration");

            String campaignPriceColor = campaignPrice.getCssValue("color");
            String campaignUnifiedColor = campaignPriceColor.replaceAll("rgb[a]+\\(","").replaceAll(", [0-9]*\\)","");
            String campaignPriceFontSize = campaignPrice.getCssValue("font-size");
            String campaignPriceTagName = campaignPrice.getAttribute("tagName");

            BigDecimal regularFontSize = new BigDecimal(regularPriceFontSize.replace("px", ""));
            BigDecimal campaignFontSize = new BigDecimal(campaignPriceFontSize.replace("px", ""));

            Assert.assertTrue(regularFontSize.compareTo(campaignFontSize) < 0);

            Assert.assertEquals("119, 119, 119", regularUnifiedColor);
            Assert.assertEquals("line-through", regularPriceStrike);

            Assert.assertEquals("204, 0, 0", campaignUnifiedColor);
            Assert.assertEquals("STRONG", campaignPriceTagName);

            driver.findElement(By.xpath("(//*[@id='box-campaigns']//li)[" + (i + 1) + "]//a")).click();


            String nameOnConcretePage = driver.findElement(By.xpath("//*[@id='box-product']//*[@class='title']")).getAttribute("textContent");

            regularPrice = driver.findElement(By.xpath("//*[@id='box-product']//*[@class='information']//*[@class='regular-price']"));
            campaignPrice = driver.findElement(By.xpath("//*[@id='box-product']//*[@class='information']//*[@class='campaign-price']"));


            String regularPriceOnConcretePage = regularPrice.getAttribute("textContent");
            String campaignPriceOnConcretePage = campaignPrice.getAttribute("textContent");

            regularPriceColor = regularPrice.getCssValue("color");
            regularUnifiedColor = regularPriceColor.replaceAll("rgb[a]+\\(","").replaceAll(", [0-9]*\\)","");

            regularPriceFontSize = regularPrice.getCssValue("font-size");
            regularPriceStrike = regularPrice.getCssValue("text-decoration");

            campaignPriceColor = campaignPrice.getCssValue("color");
            campaignUnifiedColor = campaignPriceColor.replaceAll("rgb[a]+\\(","").replaceAll(", [0-9]*\\)","");

            campaignPriceFontSize = campaignPrice.getCssValue("font-size");
            campaignPriceTagName = campaignPrice.getAttribute("tagName");

            regularFontSize = new BigDecimal(regularPriceFontSize.replace("px", ""));
            campaignFontSize = new BigDecimal(campaignPriceFontSize.replace("px", ""));

            Assert.assertTrue(regularFontSize.compareTo(campaignFontSize) < 0);


            Assert.assertEquals("102, 102, 102", regularUnifiedColor);
            Assert.assertEquals("line-through", regularPriceStrike);

            Assert.assertEquals("204, 0, 0", campaignUnifiedColor);
            Assert.assertEquals("STRONG", campaignPriceTagName);


            Assert.assertEquals("Name differ", nameOnConcretePage, nameOnMainPage);
            Assert.assertEquals("Regular price differ", regularPriceOnConcretePage, regularPriceOnMainPage);
            Assert.assertEquals("Campaign price differ", campaignPriceOnConcretePage, campaignPriceOnMainPage);

            driver.get("http://litecart.stqa.ru");
        }
    }


    @After
    public void afterActions() {
        driver.quit();
    }

}
