package test;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import spreeAppInPOM.Screenshot;

import java.io.IOException;

public class ListenersForSpree implements ITestListener {

    BasePage baseObject = new BasePage();

    @Override
    public void onTestStart(ITestResult result) {
        result.getMethod().getMethodName();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log(result.getMethod().getMethodName()+" " + "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenShotPath;
        try {
        Reporter.log(result.getMethod().getMethodName() +" "+ "FAILED");
            screenShotPath = baseObject.takeScreenShot(result.getMethod().getMethodName());
            String filePath = baseObject.attachScreenshot(screenShotPath);
            Reporter.log(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
