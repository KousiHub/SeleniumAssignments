package spreeAppInPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.BasePage;

public class LoginPage extends BasePage {
    @FindBy(css="[name='commit']")
    private WebElement loginButton;

    @FindBy(css="[type='email']")
    private WebElement loginMailID;

    @FindBy(css="[type='password']")
    private WebElement loginPassword;

    public LoginPage(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    public void sendUserName()
    {
        loginMailID.sendKeys("kousi@gmail.com");
    }
    public void sendLoginPassword()
    {
        loginPassword.sendKeys("Samsung@1");
    }
    public void clickLogInButton()
    {
        loginButton.click();
    }
}
