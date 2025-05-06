package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositaryutility.HomePage;
import com.comcast.crm.objectrepositaryutility.LoginPage;

public class Baseclass {
	
	public WebDriver driver=null;
	public static WebDriver sdriver;
	/*Create Object*/
	public DatabaseUtility dLib=new DatabaseUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public HomePage hp;

	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void beforeSuite()
	{
		System.out.println("@BeforeSuite==>Connect to DB,Report Config");
		dLib.getDbConnection();
		
		
		
	}
	
//	@Parameters("BROWSER")
//String browser
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void beforeClass() throws IOException
	{
		System.out.println("@BeforeClass==>Launch Browser");
	   String Browser = fLib.getDataFromPropertiesFile("browser");
//		String Browser=browser;
		
		if(Browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}else if(Browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}else if(Browser.equals("edge"))
		{
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		sdriver=driver;
		hp=new HomePage(driver);
		
		UtilityClassObject.setDriver(driver);
		
	}
	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void beforeMethod() throws IOException
	{
		System.out.println("@BeforeMethod==>Login");
		LoginPage lp=new LoginPage(driver);
		String Url = fLib.getDataFromPropertiesFile("url");
		String Username = fLib.getDataFromPropertiesFile("username");
		String Password = fLib.getDataFromPropertiesFile("password");
		lp.loginToapp(Url, Username, Password);
		
	
	}
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void afterMethod()
	{
		System.out.println("@AfterMethod==>Logout");
		hp.logout();
	}

	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void afterClass()
	{
		System.out.println("@AfterClass==>Close the Browser");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void afterSuite() throws SQLException
	{
		System.out.println("@AfterSuite==>Close DB,Report Backup");
		dLib.closeDbconnection();
		
	}

}
