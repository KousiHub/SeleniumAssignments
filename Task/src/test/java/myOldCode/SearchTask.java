package myOldCode;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class SearchTask extends LogAndLogOut{

    @Test
    public void SearchRuby() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("keywords")));
        driver.findElement(By.id("keywords")).sendKeys("Ruby");
        driver.findElement(By.cssSelector("input[class='btn btn-success']")).click();
        WebElement searchPanel = driver.findElement(By.id("products"));
        List<WebElement> lst = searchPanel.findElements(By.xpath("//div[contains(@id,'product_')]"));
        for(int i = 0; i < lst.size(); i++) {
            Assert.assertTrue(lst.get(i).getText().contains("Ruby"));
        }
    }

    @Test
    public void SearchApache() {
        //login("kousi@gmail.com", "Samsung@1");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("keywords")));
        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter the search word:");
        //String searchText = scanner.next();
        driver.findElement(By.id("keywords")).sendKeys("Apache");
        driver.findElement(By.cssSelector("input[class='btn btn-success']")).click();
        WebElement searchPanel = driver.findElement(By.cssSelector("#products"));
        List<WebElement> lst = searchPanel.findElements(By.xpath("//div[contains(@id,'product_')]"));
        for(int i = 0; i < lst.size(); i++) {
            Assert.assertTrue(lst.get(i).getText().contains("Apache"));
        }
        //logout();
    }
}
