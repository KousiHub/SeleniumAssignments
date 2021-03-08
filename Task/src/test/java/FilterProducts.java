import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class FilterProducts extends LogAndLogOut {

    @Test
    public void FilterByPriceOnBags() throws InterruptedException {
        //login("kousi@gmail.com", "Samsung@1");
        driver.findElement(By.xpath("//h4[text()='Shop by Categories']")).isDisplayed();
        driver.findElement(By.xpath("//a[@href='/t/bags']")).click();
        driver.findElement(By.xpath("//input[@id='Price_Range_$15.00_-_$18.00']")).click();
       // driver.findElement(By.cssSelector("#taxonomies > *:nth-child(2)>*:nth-child(1)")).click();
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
         WebElement searchPanel = driver.findElement(By.cssSelector("#products"));

        List<WebElement> lst = searchPanel.findElements(By.cssSelector("span[class = 'price selling lead']"));
         int min = 15, max = 20;
         double trimmedPrice;
         for(int i = 0; i < lst.size(); i++) {
             System.out.println(lst.get(i).getText().replace('$',' '));
             trimmedPrice = Double.parseDouble(lst.get(i).getText().replace('$',' '));
             Assert.assertTrue(trimmedPrice >= 15 && trimmedPrice <= 18);
         }
    }

}
