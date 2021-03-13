package spreeAppInPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setDriver()
    {
        System.setProperty("webdriver.chrome.driver", "/Users/kousalyasarathy/Downloads/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }
    @BeforeMethod
    public void setURL()
    {
        //driver.manage().deleteAllCookies();
        driver.get("http://spree-vapasi.herokuapp.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".nav-link.text-white")));
    }

    @AfterClass
    public  void tearDown()
    {
        driver.quit();
    }
}
