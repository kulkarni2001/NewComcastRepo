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
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustriesTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		
WebDriver driver=null;

		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();

	String Browser=fLib.getDataFromPropertiesFile("browser");
	String URL=fLib.getDataFromPropertiesFile("url");
	String Username=fLib.getDataFromPropertiesFile("username");
	String Password=fLib.getDataFromPropertiesFile("password");
	
		
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
//		
//		Random random=new Random();
//		int randomInt = random.nextInt(1000);
		
		
		//read testScript data from Excel File
//		FileInputStream fis1=new FileInputStream("C:\\Users\\HP\\Desktop\\TestScriptData.xlsx");
//		Workbook wb = WorkbookFactory.create(fis1);
//		Sheet sheet = wb.getSheet("Org");
//		Row row = sheet.getRow(4);
		
		String orgName=eLib.getDataFromExcel("Org", 4, 2)+jLib.getRandomNumber();
		String industry=eLib.getDataFromExcel("Org", 4, 3);
		String type=eLib.getDataFromExcel("Org", 4, 4);
		
		
		
		
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
		WebElement ele1 = driver.findElement(By.name("industry"));
		Select sel=new Select(ele1);
		sel.selectByVisibleText(industry);
		
		WebElement ele2 = driver.findElement(By.name("accounttype"));
		Select sel2=new Select(ele2);
		sel2.selectByVisibleText(type);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(2000);
		
		
		
		//verify Header orgName info Expected Result
		String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		if(actOrgName.contains(orgName))
		{
			System.out.println(orgName+" is created ==>PASS ");
		}else {
			System.out.println(orgName+" is not created ==>FAIL");
		}
		
		//verify the industries and type info
		
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustries.equals(industry))
		{
			System.out.println(industry +" information is verified ==>PASS");
		}else {
			System.out.println(industry+" information is not verified==>FAIL");
		}
		
		
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type))
		{
			System.out.println(type +" information is verified ==>PASS");
		}else {
			System.out.println(type +" information is not verified==>FAIL");
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
