package myOldCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class LogAndLogOut {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;

    public void login(String emailId, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("link-to-login")));
        driver.findElement(By.id("link-to-login")).click();
        driver.findElement(By.id("spree_user_email")).sendKeys(emailId);
        driver.findElement(By.id("spree_user_password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name ='commit']")).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Logout")));
        driver.findElement(By.partialLinkText("Logout")).click();
    }

    @BeforeClass
    public void setDriverURL() {
        System.setProperty("webdriver.chrome.driver", "/Users/kousalyasarathy/Downloads/chromedriver");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void setURLAndLogin() {
        driver.get("https://spree-vapasi.herokuapp.com");
        wait = new WebDriverWait(driver, 100);
        driver.manage().window().maximize();
        login("kousi@gmail.com", "Samsung@1");
    }

    @AfterMethod
    public void Logout() {
        logout();
    }

    @Test
    public void assertLoginMsg() {
        //login("kousi@gmail.com", "Samsung@1");
        assertEquals(driver.findElement(By.cssSelector("div[class='alert alert-success']")).getText(), "Logged in successfully");
    }

    @Test
    public void assertLogoutMsg() {
        //logout();
        assertEquals(driver.findElement(By.cssSelector("div[class='alert alert-notice']")).getText(), "Signed out successfully.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();

    }
}
