package com.INetBanking.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 *  Creating Page Object Class
 */
public class LoginPage

{
	
	public WebDriver ldriver;

	public LoginPage(WebDriver rdriver) 
	{
		ldriver=rdriver;  //remote driver
		PageFactory.initElements(rdriver, this); // creating Constructor
	}
	
	
	@FindBy(name="uid")
	@CacheLookup
	private WebElement txtUserName;
	
	
	@FindBy(name="password")
	@CacheLookup
	private WebElement txtPassword;
	
	
	@FindBy(name="btnLogin")
	private WebElement btnLogin;
	
	@FindBy(xpath="//a[contains(text(),'Log out')]")
	private WebElement btnLogout;
	
	 /*
	  *  Actions Method
	  */
	
	// UserName Method
	public void setUserName(String uname) throws InterruptedException
	{
		txtUserName.sendKeys(uname);
		
	}
	
	// Password Method
	public void setPassword(String pwd) throws InterruptedException
	{
		
		txtPassword.sendKeys(pwd);
		
	}
	
	// Login Method
	public void clickSubmit() throws Exception
	{
		btnLogin.click();
		Thread.sleep(3000);
	}
	
	
	public void logout() throws Exception
	{
		btnLogout.click();
		Thread.sleep(3000);
	}
	
	
	
	
}
