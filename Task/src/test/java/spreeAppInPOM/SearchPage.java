package spreeAppInPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import test.BasePage;

import java.util.List;

public class SearchPage extends BasePage {
    @FindBy (css="#keywords")
    private WebElement searchField;
    @FindBy (xpath="//input[@type='submit']")
    private WebElement searchButton;
    @FindBys(@FindBy (xpath="//div[contains(@id,'product_')]"))
    private List<WebElement> searchResultProducts;

    public SearchPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean searchAndValidateProductsAgainstInput(String prodName) {
        searchField.clear();
        searchField.sendKeys(prodName);
        searchButton.click();
        for(WebElement product: searchResultProducts){
            WebElement name = product.findElement(By.cssSelector("[itemprop='name']"));
            String nameLeveled = name.getText().trim().toLowerCase();
            if(!nameLeveled.contains(prodName.toLowerCase()))
                return false;
        }
        return true;
    }

}
