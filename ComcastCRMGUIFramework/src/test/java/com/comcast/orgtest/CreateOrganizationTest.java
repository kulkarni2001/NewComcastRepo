package com.comcast.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.values.JavaUriHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositaryutility.HomePage;
import com.comcast.crm.objectrepositaryutility.LoginPage;
import com.comcast.crm.objectrepositaryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositaryutility.OrganizationsPage;

public class CreateOrganizationTest extends Baseclass{
	
	@Test
	public void createOrganization() throws EncryptedDocumentException, IOException
	{
		String orgName=eLib.getDataFromExcel("Org", 1, 2)+jLib.getRandomNumber();
		
		hp.getOrgLink().click();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName=oip.getHeaderMsg().getText();
		
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+" name is verified ==>PASS");
		}else
		{
			System.out.println(orgName+" name is not verified==>FAIL");
		}
		
		
		
	}

}
		
		
		//verify Header orgName info Expected Result
//		String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		
	
//	public static void main(String[] args) throws IOException, InterruptedException {
//		
//WebDriver driver=null;
//
//	FileUtility fLib=new FileUtility();
//	ExcelUtility eLib=new ExcelUtility();
//	JavaUtility jLib=new JavaUtility();
//	
//	String Browser=fLib.getDataFromPropertiesFile("browser");
//	String URL=fLib.getDataFromPropertiesFile("url");
//	String Username=fLib.getDataFromPropertiesFile("username");
//	String Password=fLib.getDataFromPropertiesFile("password");
//	
//		
//		if(Browser.equals("chrome"))
//		{
//			driver=new ChromeDriver();
//		}else if(Browser.equals("firefox"))
//		{
//			driver=new FirefoxDriver();
//		}else if(Browser.equals("edge"))
//		{
//			driver=new EdgeDriver();
//		}else {
//			driver=new ChromeDriver();
//		}
//		
//		
//		
//		//generate the random number
//		
//		
//		//read testScript data from Excel File
//		
//		
//		String orgName=eLib.getDataFromExcel("Org", 1, 2)+jLib.getRandomNumber();
//	
//		
//		
//		
//		//step 1:login to app
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get(URL);
//		
//		LoginPage lp = new LoginPage(driver);
//		lp.loginToapp(URL,Username, Password);
//		
////		lp.getUsernameEdt().sendKeys("admin");
////		lp.getPasswordEdt().sendKeys("admin");
////		lp.getLoginBtn().click();
////		driver.findElement(By.name("user_name")).sendKeys(Username);
////		driver.findElement(By.name("user_password")).sendKeys(Password);
////		driver.findElement(By.id("submitButton")).click();
////		
//		//step 2:navigate to Organization module
//		
//		HomePage hp=new HomePage(driver);
//		hp.getOrgLink().click();
//	
////		driver.findElement(By.linkText("Organizations")).click();
//		
////		//step 3:click on "Create Organizations" Button
//		
//		OrganizationsPage op=new OrganizationsPage(driver);
//		op.getCreateNewOrgBtn().click();
////		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
////		
////		//step 4:enter all the details & create new organization
//		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
//		cnop.createOrg(orgName);
////		driver.findElement(By.name("accountname")).sendKeys(orgName);
////		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
////		Thread.sleep(2000);
////		
////		
////		//verify Header msg Expected Result
//
//		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
//		String actOrgName=oip.getHeaderMsg().getText();
//		
//		if(actOrgName.contains(orgName)) {
//			System.out.println(orgName+" name is verified ==>PASS");
//		}else
//		{
//			System.out.println(orgName+" name is not verified==>FAIL");
//		}
//		
//		hp.logout();
//		
////		String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
////		if(headerInfo.contains(orgName))
////		{
////			System.out.println(orgName+" is created ==PASS ");
////		}else {
////			System.out.println(orgName+" is not created ==FAIL");
////		}
////		
////		//verify Header orgName info Expected Result
////		String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
////		
////		if(actOrgName.contains(orgName))
////		{
////			System.out.println(orgName+" is created ==PASS ");
////		}else {
////			System.out.println(orgName+" is not created ==FAIL");
////		}
////		
////		
////		
////		
////		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
////		ele.click();
////		
////		Actions act=new Actions(driver);
////		act.moveToElement(ele).perform();
////		driver.findElement(By.linkText("Sign Out")).click();
//		
//		
////		
//		
//		Thread.sleep(2000);
//		driver.quit();
//		
//		

