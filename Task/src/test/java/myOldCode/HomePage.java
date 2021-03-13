package myOldCode;

import test.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(css=".nav-link.text-white")
    private WebElement loginLink;
    @FindBy(xpath = "//a[@href='/t/bags']")
    private WebElement categoryBags;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickLogInLink()
    {
        loginLink.click();
    }
    public void clickCategoryBags()
    {
        categoryBags.click();
    }
}
