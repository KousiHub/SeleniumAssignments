package myOldCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class ClearCart extends LogAndLogOut{
    @Test
    public void SearchByRubyOnRailsMug() throws InterruptedException {

        driver.findElement(By.xpath("//h4[text()='Shop by Categories']")).isDisplayed();
        driver.findElement(By.xpath("//a[@href='/t/mugs']")).click();
        WebElement product = driver.findElement(By.cssSelector("span[title = 'Ruby on Rails Mug']"));
        System.out.println(product.getText());
        driver.findElement(By.cssSelector("span[title = 'Ruby on Rails Mug']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("quantity")));
        driver.findElement(By.id("quantity")).sendKeys("3");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        driver.findElement(By.id("add-to-cart-button")).click();
        //Thread.sleep(100);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
        driver.findElement(By.name("commit")).click();
        String actualMsg = driver.findElement(By.className("alert-info")).getText();
        Assert.assertEquals(actualMsg,"Your cart is empty");
    }
}
