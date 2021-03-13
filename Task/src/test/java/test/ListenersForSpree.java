package test;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ListenersForSpree implements ITestListener {

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
        Reporter.log(result.getMethod().getMethodName() +" "+ "FAILED");
    }
}
