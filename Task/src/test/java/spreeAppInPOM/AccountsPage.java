package spreeAppInPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountsPage extends BasePage {

    @FindBy(xpath="//a[@href='/logout']")
    private WebElement logoutButton;
    @FindBy (css="#keywords")
    private WebElement searchField;
    @FindBy (xpath="//input[@type='submit']")
    private WebElement searchButton;
    @FindBy (css =".alert.alert-success")
    private WebElement loginMsg;
    @FindBy (css =".alert.alert-notice")
    private WebElement logOutMsg;
    @FindBys(@FindBy (css="#products"))
    private List<WebElement> allProducts;

    public AccountsPage(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    public void clickLogOutButton()
    {
        logoutButton.click();
    }
    public String getLoginMessage()
    {
        return loginMsg.getText();
    }
    public String getLogOutMessage()
    {
        return logOutMsg.getText();
    }

}
