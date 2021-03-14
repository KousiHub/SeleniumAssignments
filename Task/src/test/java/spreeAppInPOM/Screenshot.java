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
    /**String CHROME_KEY = "webdriver.chrome.driver";
    String CHROME_VALUE = "/Users/kousalyasarathy/Downloads/chromedriver";
    String TEST_URL = "https://spree-vapasi.herokuapp.com";

    public static void takeScreenShot(WebDriver driver, String fileNameForScreenShot) throws IOException {
        Date d = new Date();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir")+"//Screenshots//"+fileNameForScreenShot+".png");
        FileUtils.copyFile(source,destination);
    }**/
}
/**
 String dest=System.getProperty("user.dir")+"//Screenshots//"+format+".png";
 File destination=new File(dest);
 FileUtils.copyFile(name,new File(dest));
 InputStream is=new FileInputStream(dest);
 byte[] imageBytes= FileUtils.readFileToByteArray(destination);
 String base64= "data:image/png;base64,"+ Base64.getEncoder().encodeToString(imageBytes);
 return base64;
 **/

