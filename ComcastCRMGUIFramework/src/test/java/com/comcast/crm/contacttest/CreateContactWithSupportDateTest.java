package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import com.comcast.crm.objectrepositaryutility.ContactPage;
import com.comcast.crm.objectrepositaryutility.CreatingNewContactPage;

public class CreateContactWithSupportDateTest {
	
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
				
				Random random=new Random();
				int randomInt = random.nextInt(1000);
				
				
				//read testScript data from Excel File
				
				String lastName=eLib.getDataFromExcel("Contact", 4, 2)+jLib.getRandomNumber();
		
				//step 1:login to app
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(Username);
				driver.findElement(By.name("user_password")).sendKeys(Password);
				driver.findElement(By.id("submitButton")).click();
				
				//step 2:navigate to Contacts module
				ContactPage cp=new ContactPage(driver);
				cp.getGetCreateContactBtn().click();
				//driver.findElement(By.linkText("Contacts")).click();
				
				//step 3:click on "Create Contact" Button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//step 4:enter all the details & create new organization
				
//				Date dateObj=new Date();
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//				String startDate = sdf.format(dateObj);
//				
//				Calendar calender = sdf.getCalendar();
//				calender.add(Calendar.DAY_OF_MONTH,30);
//				String endDate=sdf.format(calender.getTime())
				
				String startDate = jLib.getSystemDateYYYYMMDD();
				String endDate=jLib.getRequiredDateYYYYMMDD(30);
				
//				driver.findElement(By.name("lastname")).sendKeys(lastName);
//				
//				driver.findElement(By.name("support_start_date")).clear();
//				driver.findElement(By.name("support_start_date")).sendKeys(startDate);
//				
//				driver.findElement(By.name("support_end_date")).clear();
//				driver.findElement(By.name("support_end_date")).sendKeys(endDate);
//				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				CreatingNewContactPage ccp=new CreatingNewContactPage(driver);
				ccp.createContactWithSupportDate(lastName, startDate, endDate);
				Thread.sleep(2000);
				
				
				//verify start Date and End Date
								
				String actualStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if(actualStartDate.equals(startDate))
				{
					System.out.println(startDate +"  information is verified ==>PASS ");
				}else {
					System.out.println(startDate +"  information is not verified ==>FAIL");
				}
				
				String actualEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(actualEndDate.equals(endDate))
				{
					System.out.println(endDate +"  information is verified ==>PASS ");
				}else {
					System.out.println(endDate +"  information is not verified ==>FAIL");
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
