package com.comcast.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithPhoneNumberTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		
	

//	FileInputStream fis=new FileInputStream("C:\\Users\\HP\\Desktop\\VtigerCommonData.txt");
//	Properties prop=new Properties();
//	prop.load(fis);
		
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
	String Browser=fLib.getDataFromPropertiesFile("browser");
	String URL=fLib.getDataFromPropertiesFile("url");
	String Username=fLib.getDataFromPropertiesFile("username");
	String Password=fLib.getDataFromPropertiesFile("password");
	
	WebDriver driver=null;
		
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
		
		
		
		//generate the random number
		
//		Random random=new Random();
//		int randomInt = random.nextInt(1000);
//		
//		
//		//read testScript data from Excel File
//		FileInputStream fis1=new FileInputStream("C:\\Users\\HP\\Desktop\\TestScriptData.xlsx");
//		Workbook wb = WorkbookFactory.create(fis1);
//		Sheet sheet = wb.getSheet("Org");
//		Row row = sheet.getRow(7);
		
		String orgName=eLib.getDataFromExcel("Org", 7, 2)+jLib.getRandomNumber();
		String phoneNumber=eLib.getDataFromExcel("Org",7,3);
		
		//step 1:login to app
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		
		//step 2:navigate to Organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 3:click on "Create Organizations" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4:enter all the details & create new organization
		
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(2000);
		
		
		//verify Header Phone Number info Expected Result
		String actPhoneNo=driver.findElement(By.id("dtlview_Phone")).getText();
		
		if(actPhoneNo.contains(phoneNumber))
		{
			System.out.println(phoneNumber +" Information is Verified ==> PASS ");
		}else {
			System.out.println(orgName+" Information is not Verified ==> FAIL");
		}
		
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		ele.click();
		
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		
		Thread.sleep(2000);
		driver.quit();
		
		
	}

}
