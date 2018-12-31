package com.INetBanking.Base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.INetBanking.Utility.ReadConfig;

public class BaseClass {
	// SDET tutorials
	ReadConfig readconfig = new ReadConfig();

	public String baseUrl = readconfig.getApplicationUrl();
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public static WebDriver driver;

	public static Logger logger; // Adding Logger

	// This method will execute @BeforeClass
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) throws Exception {

		logger = Logger.getLogger(""); // Adding Logger
		PropertyConfigurator.configure("Log4j.properties"); // Adding Logger

		if (br.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
		}

		else if (br.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();

		}

		else if (br.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();

		}

		driver.get(baseUrl);

		// Global Implicit
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	/*
	 * CaptureScreen Method
	 */

	public void captureScreen(WebDriver driver, String tname) throws IOException
	// will take 2 parameters driver and testcase name
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");

	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}
