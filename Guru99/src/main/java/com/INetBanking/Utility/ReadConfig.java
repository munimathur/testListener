package com.INetBanking.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig
{
	
	public static Properties pro;
	
	public ReadConfig()  // Created Constructor to load the file
	{
		File src=new File ("./Cofiguration/Config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			try {
				pro.load(fis);
			} catch (IOException e) 
			{
				System.out.println("Exception is "+e.getMessage());
				
			}
		} catch (FileNotFoundException e) 
		{
			System.out.println("Exception is "+e.getMessage());
		}
		
	}
	
	
		public String getApplicationUrl()
		{
			//String url=pro.getProperty("baseUrl");
			return pro.getProperty("baseUrl");
		}
		
		public String getUserName()
		{
			String username=pro.getProperty("username");
			return username;
		}
		
		public String getPassword()
		{
			String password=pro.getProperty("password");
			return password;
		}
		
		public String getChromePath()
		{
			String chromePath=pro.getProperty("chromePath");
			return chromePath;
		}

		
		public String getFirefoxPath()
		{
			String firefoxPath=pro.getProperty("firefoxPath");
			return firefoxPath;
		}
		
		public String getIEPath()
		{
			String getIEPath=pro.getProperty("getIEPath");
			return getIEPath;
		}
}
