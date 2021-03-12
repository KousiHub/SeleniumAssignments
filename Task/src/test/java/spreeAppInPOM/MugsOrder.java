package spreeAppInPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MugsOrder extends BasePage {
    @FindBy(xpath = "//a[@href='/t/mugs']")
    private WebElement mugsButton;
    @FindBy(xpath="//*[text()='Ruby on Rails Mug']")
    private WebElement mugRubyOnRailsTote;
    //@FindBy(css = "span[title = 'Ruby on Rails Mug']")
    //private WebElement clickLinkToSelect;
    @FindBy(id="add-to-cart-button")
    private WebElement addToCartButton;
    @FindBy(id="checkout-link")
    private WebElement checkoutButton;
    @FindBy(xpath="//*[@class='warning cart-total']//following::td[2]")
    private WebElement totalPriceOnCheckout;

    public MugsOrder(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void priceOfSelectedMugOnCheckout() {
        mugsButton.click();
        mugRubyOnRailsTote.click();
        addToCartButton.click();
    }
}
