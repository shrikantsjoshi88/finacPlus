package Listeners;

import CommonMethodsAndUtilities.CommonMethodsAndUtilities;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class TestListener extends CommonMethodsAndUtilities implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Reporter.log("onStart method " + iTestContext.getName(), true);
        iTestContext.setAttribute("WebDriver", CommonMethodsAndUtilities.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Reporter.log("OnFinish method " + iTestContext.getName(), true);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Reporter.log("onTestStart method " + getTestMethodName(iTestResult) + " started", true);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Reporter.log("onTestSuccess method " + getTestMethodName(iTestResult) + " succeed", true);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Reporter.log("onTestFailure method " + getTestMethodName(iTestResult) + " failed", true);
        takeScreenShot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Reporter.log("onTestSkipped method " + getTestMethodName(iTestResult) + " skipped", true);
    }
}
