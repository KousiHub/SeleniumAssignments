package spreeAppInPOM;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    public static ExtentReports extentreport()
    {
        String path=System.getProperty("user.dir")+"//Reports/extentreport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setReportName("Report - Spree Application Automation");

        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Kousalya");

        return extent;
    }
}
