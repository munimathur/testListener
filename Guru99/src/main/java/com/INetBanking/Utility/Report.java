package com.INetBanking.Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Listeners class methods automatically invoked whenever methods are passed failed ,skipped.

public class Report extends TestListenerAdapter // TestListener Adapter is a predefined class which contain 4 methods
												// Like method overriding
{

	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	ExtentTest logger;

	public void onTestStart(ITestContext testContext) // These methods can not be changed
	{
		// adding time stamp to report , SimpleDateFormat is pre defined class in java ,
		// pass the format of date in this class.
		// new report will generate according to time stamp to avoid duplicay
		// Dynamic thing for new report

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.attachReporter();
		extent.setSystemInfo("Host name", "localHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Muni");

		htmlReporter.config().setDocumentTitle("My Test"); // Title of Report
		htmlReporter.config().setReportName("Funvtional Test Report"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test1");
	}

	public void onTestSuccess(ITestResult tr) // Every methods requires parameters
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr) // ITestResult is a class and tr is obj
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // Every method take
																							// ITEstResult as a object

		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";

		File f = new File(screenshotPath);

		// To check whether screenshot is available or not ,file is exists or not
		if (f.exists()) {
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) 
	{
		//extent.flush();
	}

}
