package spreeAppInPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends BasePage {

    @FindBy(id = "delete_line_item_1458")
    private WebElement deleteOneItemFromCartUsingMinusSign;
    @FindBy(xpath = "//input[@value='Empty Cart']")
    private WebElement emptyCartButton;
    @FindBy(xpath = "//td[@class='lead']")
    private WebElement totalAmountOnCart;
    @FindBy(id = "checkout-link")
    private WebElement checkoutLink;
    @FindBy(xpath = "//div[@class='alert alert-info']")
    private WebElement emptyCartMessage;
    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement quantityRequired;

    Double priceOnShoppingCart;

   public ShoppingCartPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String emptyCart() throws InterruptedException {
        Thread.sleep(1000);
        emptyCartButton.click();
        return emptyCartMessage.getText();
    }

    public void deleteOneItem() {
        deleteOneItemFromCartUsingMinusSign.click();
    }

    public void addQuantity(int quantity)
    {
        quantityRequired.clear();
        quantityRequired.sendKeys(String.valueOf(quantity));
    }

    public double getTotalAmountAndCheckout() throws InterruptedException {
        Thread.sleep(100);
        priceOnShoppingCart = Double.parseDouble(totalAmountOnCart.getText().replace('$',' '));
        //System.out.println("Total amount on cart: " +priceOnShoppingCart);
        checkoutLink.click();
        return priceOnShoppingCart;
    }

}
