package myOldCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class ValidateOrderAmount extends LogAndLogOut{
    @Test
    public void SearchByRubyOnRailsMug() throws InterruptedException {

        driver.findElement(By.xpath("//h4[text()='Shop by Categories']")).isDisplayed();
        driver.findElement(By.xpath("//a[@href='/t/mugs']")).click();
        WebElement product = driver.findElement(By.cssSelector("span[title = 'Ruby on Rails Mug']"));
        System.out.println(product.getText());
        double priceWhenSelected,priceInCheckout;
        WebElement priceSelectedElement = driver.findElement(By.cssSelector("span[class = 'price selling lead']"));
        priceWhenSelected = Double.parseDouble(priceSelectedElement.getText().replace('$',' '));
        System.out.println(priceWhenSelected);
        driver.findElement(By.cssSelector("span[title = 'Ruby on Rails Mug']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        driver.findElement(By.id("add-to-cart-button")).click();
        Thread.sleep(100);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout-link")));
        driver.findElement(By.id("checkout-link")).click();
        WebElement priceCheckoutElement = driver.findElement(By.id("summary-order-total"));
        //System.out.println(driver.findElement(By.id("summary-order-total")).getText());
        priceInCheckout= Double.parseDouble(priceCheckoutElement.getText().replace('$',' '));
        System.out.println(priceInCheckout);
        Assert.assertEquals(priceInCheckout,priceWhenSelected);
    }
}
