package com.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReporterListener implements ITestListener {

    private static ExtentReports extent;
    // ThreadLocal ensures thread safety for parallel execution
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports createReport() {
        // Create a unique report name using a timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss"));
        String reportFileName = "Test-Report-" + timestamp + ".html";

        // Define the report output path
        String path = System.getProperty("user.dir") + "/test-reports/" + reportFileName;

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("TA Selenium Test Results");
        reporter.config().setDocumentTitle("Automation Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }

    @Override
    public void onStart(ITestContext context) {
        // Initialize the report instance once
        if (extent == null) {
            extent = createReport();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test node in the report for each @Test method
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed");
        // Attach the failure details (stack trace)
        extentTest.get().fail(result.getThrowable());

        // FUTURE: This is where you would add your Screenshot logic!
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Write the final results to the HTML file (CRITICAL step)
        if (extent != null) {
            extent.flush();
        }
    }

    // Utility method to allow test classes to get the current test for logging
    public static ExtentTest getTest() {
        return extentTest.get();
    }
}