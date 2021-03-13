package spreeAppInPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.BasePage;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "summary-order-total")
    private WebElement orderTotalOnCheckout;
    Double priceOnCheckoutCart;

    public double getOrderTotal(){
        priceOnCheckoutCart = Double.parseDouble(orderTotalOnCheckout.getText().replace('$',' '));
        return priceOnCheckoutCart;
    }
}
