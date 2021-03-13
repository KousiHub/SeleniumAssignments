package spreeAppInPOM;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;

public class Screenshot {
    String CHROME_KEY = "webdriver.chrome.driver";
    String CHROME_VALUE = "/Users/kousalyasarathy/Downloads/chromedriver";
    String TEST_URL = "https://spree-vapasi.herokuapp.com";

    public void takeScreenShot(WebDriver driver) throws IOException {
        Date d = new Date();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir")+"//Screenshots//"+".png");
        FileUtils.copyFile(source,destination);
    }
}
