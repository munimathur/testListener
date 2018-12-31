package com.INetBankingTestCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.INetBanking.Base.BaseClass;
import com.INetBanking.PageObjects.LoginPage;

public class Test002 extends BaseClass
 
{
	
	@Test//(dataProvider="LoginData")
	public void loginDT() throws Exception 
	{
		
    LoginPage lp=new LoginPage(driver);
	lp.setUserName(username);
	lp.setPassword(password);
	lp.clickSubmit();
	
	Thread.sleep(3000);
	
	if(isAlertPresent()==true) 
	
	{
		captureScreen(driver,"Login");
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		logger.info("login failed");
		Thread.sleep(3000);
		
	}
	else
	{
		Assert.assertTrue(true);
		lp.logout();
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
	}
	
 }	

public boolean isAlertPresent() 
{
	try
	{
	driver.switchTo().alert();
	return true;
	}
	catch(NoAlertPresentException e)
	{
		return false;
	}
	
}

}