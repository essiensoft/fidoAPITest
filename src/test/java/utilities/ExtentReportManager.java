package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    private static final String REPORT_PATH = ".\\reports\\";
    private ExtentSparkReporter reporter;
    private ExtentReports report;
    private ExtentTest test;
    private String reportName;

    @Override
    public void onStart(ITestContext testContext) {
        reportName = "Test-Report-" + getCurrentTimeStamp() + ".html";
        reporter = new ExtentSparkReporter(REPORT_PATH + reportName);
        configureReporter(reporter);

        report = new ExtentReports();
        report.attachReporter(reporter);
        setSystemInfo();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        createTestNode(result, Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        createTestNode(result, Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        createTestNode(result, Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        report.flush();
    }

    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    private void configureReporter(ExtentSparkReporter reporter) {
        reporter.config().setDocumentTitle("FIDO API TEST");
        reporter.config().setReportName("Video Game DB 2022 API");
        reporter.config().setTheme(Theme.DARK);
    }

    private void setSystemInfo() {
        report.setSystemInfo("Application", "Video Game DB 2022 API");
        report.setSystemInfo("Operating System", System.getProperty("os.name"));
        report.setSystemInfo("Environment", "QA");
        report.setSystemInfo("User", "Felix");
    }

    private void createTestNode(ITestResult result, Status status, String message) {
        test = report.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(status, message);
    }
}