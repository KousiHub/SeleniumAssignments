package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

public class BasePage {

    public static WebDriver driver;
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

    public String takeScreenShot(String fileNameForScreenShot) throws IOException {

        Date d = new Date();
        System.out.println("Driver is" +BasePage.driver);
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest=System.getProperty("user.dir")+"//Screenshots//"+fileNameForScreenShot+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source,new File(dest));

        InputStream is=new FileInputStream(dest);
        byte[] imageBytes= FileUtils.readFileToByteArray(destination);
        String base64= "data:image/png;base64,"+ Base64.getEncoder().encodeToString(imageBytes);
        return base64;
    }

    public static String attachScreenshot(String filepath)
    {
        System.out.println(filepath);
        String path="<br><img src='"+filepath+"' height='400' width='400'/><br>";
        return path;
    }

    @AfterClass
    public  void tearDown()
    {
        driver.quit();
    }

}
