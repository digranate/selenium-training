package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by dinara.trifanova on 3/24/2017.
 */
public class MainPage extends Page{
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void open() {
        driver.get("http://litecart.stqa.ru");
    }
    public int getQuantity(){
        return Integer.parseInt(driver.findElement(By.className("quantity")).getAttribute("textContent"));
    }
    public void openProduct(int productNum){
        driver.findElements(By.xpath("//ul[@class='listing-wrapper products']/li")).get(productNum).click();
    }

}

