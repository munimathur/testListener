package com.INetBankingTestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.INetBanking.Base.BaseClass;
import com.INetBanking.PageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass

{
	 
			@Test
			public void Login() throws Exception 
			
			{
												
				LoginPage lp=new LoginPage(driver); //Creating a object and pass driver as a parameter bcos of constructor 
				
				lp.setUserName(username);
				logger.info("Entering the UserName");
				System.out.println(username);
				
				lp.setPassword(password);
				logger.info("Entering the Password");
				System.out.println(password);
				
				lp.clickSubmit();
				logger.info("Click on submit");
				
				if(driver.getTitle().equals("GTPL Bank Manager HomePage"))
				{
					Assert.assertTrue(true);
					logger.info("Test Case Passed");
				}
				else
				{
					captureScreen(driver,"Login");
					Assert.assertFalse(false);
					logger.info("Test Case Failed");
					
				}
			
			}
              
					
}
