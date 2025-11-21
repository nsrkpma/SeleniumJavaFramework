package Kane.SeleniumJavaFramework.TestComponents;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.qameta.allure.Allure;
import Kane.SeleniumJavaFramework.resources.ExtentReporterNg;

public class ListenersTwo extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNg.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        // Log to Extent
        extentTest.get().fail(result.getThrowable());

        // retrieve driver instance from test class (as you already do)
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1) Extent screenshot (you already have)
        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (filePath != null) {
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        }

        // 2) Add screenshot to Allure as attachment (bytes)
        try {
            if (driver != null) {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                // Attach screenshot - shows inline in Allure
                Allure.addAttachment(result.getMethod().getMethodName() + " - screenshot",
                        new ByteArrayInputStream(screenshotBytes));

                // Attach page source (optional) - helpful for debugging
                String pageSource = driver.getPageSource();
                Allure.addAttachment("Page source - " + result.getMethod().getMethodName(),
                        "text/html",
                        new ByteArrayInputStream(pageSource.getBytes(StandardCharsets.UTF_8)),
                        ".html");
            }
        } catch (Exception e) {
            // don't block test flow on attachment errors - just log
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }
}
