package spreeAppInPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.BasePage;

import java.util.List;

public class FilterProductByPrice extends BasePage {

    @FindBy(xpath="//a[@href='/t/bags']")
    public WebElement productBag;
    @FindBy(xpath="//input[@id='Price_Range_$15.00_-_$18.00']")
    public WebElement priceRange;
    @FindBy(xpath="//input[@class='btn btn-primary']")
    public WebElement searchButton;
    @FindBy (id="products")
    public WebElement productsPanel;

    public FilterProductByPrice(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean filterByPrice(int min, int max) {
        productBag.click();
        priceRange.click();
        searchButton.click();
        List<WebElement> lst = productsPanel.findElements(By.cssSelector("span[class = 'price selling lead']"));
        double trimmedPrice;
        for(int i = 0; i < lst.size(); i++) {
            //System.out.println(lst.get(i).getText().replace('$',' '));
            trimmedPrice = Double.parseDouble(lst.get(i).getText().replace('$',' '));
            if(!(trimmedPrice >= min) && (trimmedPrice <= max))
                return false;
        }
        return true;
    }

}
