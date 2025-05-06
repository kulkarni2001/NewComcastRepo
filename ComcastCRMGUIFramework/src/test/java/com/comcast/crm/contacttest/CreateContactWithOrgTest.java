package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.objectrepositaryutility.ContactPage;
import com.comcast.crm.objectrepositaryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.objectrepositaryutility.SelectOrganizationPage;

public class CreateContactWithOrgTest extends Baseclass{
	
//		@Test
//		public void demo() throws IOException, InterruptedException {
//			
////			WebDriver driver=null;
////			
////			FileUtility fLib=new FileUtility();
////			ExcelUtility eLib=new ExcelUtility();
////			JavaUtility jLib=new JavaUtility();
////			WebDriverUtility wLib=new WebDriverUtility();
////			
////				String Browser=fLib.getDataFromPropertiesFile("browser");
////				String URL=fLib.getDataFromPropertiesFile("url");
////				String Username=fLib.getDataFromPropertiesFile("username");
////				String Password=fLib.getDataFromPropertiesFile("password");
////				
////					
////					if(Browser.equals("chrome"))
////					{
////						driver=new ChromeDriver();
////					}else if(Browser.equals("firefox"))
////					{
////						driver=new FirefoxDriver();
////					}else if(Browser.equals("edge"))
////					{
////						driver=new EdgeDriver();
////					}else {
////						driver=new ChromeDriver();
////					}
//					
//					
//					
//					//generate the random number
//					//read testScript data from Excel File
//					String orgName=eLib.getDataFromExcel("Contact", 7, 2)+ jLib.getRandomNumber();
//					String contactLastName = eLib.getDataFromExcel("Contact", 7, 3);
//				
//					
//					
//					//step 1:login to app
////					driver.manage().window().maximize();
////					wLib.waitForPageLoad(driver);
////					driver.get(URL);
////					driver.findElement(By.name("user_name")).sendKeys(Username);
////					driver.findElement(By.name("user_password")).sendKeys(Password);
////					driver.findElement(By.id("submitButton")).click();
//					
//					//step 2:navigate to Organization module
//					//driver.findElement(By.linkText("Organizations")).click();
//					hp.getOrgLink().click();
//					
//					//step 3:click on "Create Organizations" Button
//					
//					OrganizationsPage op=new OrganizationsPage(driver);
//					op.getCreateNewOrgBtn().click();
//					CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
//					cnop.createOrg(orgName);
//				
//				//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
//					
//					//step 4:enter all the details & create new organization
//					
//					
////					driver.findElement(By.name("accountname")).sendKeys(orgName);
////					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
////					Thread.sleep(2000);
//					
//					
//					//verify Header msg Expected Result
//					
//					String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//					if(headerInfo.contains(orgName))
//					{
//						System.out.println(orgName+" is created ==PASS ");
//					}else {
//						System.out.println(orgName+" is not created ==FAIL");
//					}
//					
//					//Step 5:Navigate to contact Module
//					
//					hp.getContactLink().click();
//					
//					
//					//driver.findElement(By.linkText("Contacts")).click();
//					
//					//step 6:click on "Create Contact" Button
//					
//					ContactPage cp=new ContactPage(driver);
//					cp.getGetCreateContactBtn().click();
//					//driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
//					
//					//step 7:enter all the details & create new organization
//					
//					CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
//					cncp.createContact(contactLastName);
//				//	driver.findElement(By.name("lastname")).sendKeys(contactLastName);
//					
//					cncp.getOrgPlusBtn().click();
//				//	driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
//					//switch to child window
//					wLib.switchToTabOnURL(driver,"module=Accounts");
//					
//				
//					SelectOrganizationPage sop=new SelectOrganizationPage(driver);
//					sop.getSearchOrgEdt().sendKeys(orgName+Keys.ENTER);
////					driver.findElement(By.name("search_text")).sendKeys(orgName+Keys.ENTER);
//					Thread.sleep(2000);
//					driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
//				
//					cncp.getSaveBtn().click();
////					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//					Thread.sleep(2000);
//					
//
//					//verify Header msg Expected Result
//					
//					 headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//					if(headerInfo.contains(contactLastName))
//					{
//						System.out.println(contactLastName+" is created ==>PASS ");
//					}else {
//						System.out.println(contactLastName+" is not created ==>FAIL");
//					}
//					
//					//verify Header orgName info Expected Result
//					String actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
//					
//					if(actOrgName.contains(orgName))
//					{
//						System.out.println(orgName+" is created ==>PASS ");
//					}else {
//						System.out.println(orgName+" is not created ==>FAIL");
//					}
//							
//					WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//					ele.click();
//					
//					Actions act=new Actions(driver);
//					act.moveToElement(ele).perform();
//					driver.findElement(By.linkText("Sign Out")).click();
//						
//					Thread.sleep(2000);
//					driver.quit();				
//	}
//	
		
		@Test
		public void demo1() throws EncryptedDocumentException, IOException, InterruptedException
		{
			String orgName=eLib.getDataFromExcel("Contact", 7, 2)+ jLib.getRandomNumber();
			String contactLastName = eLib.getDataFromExcel("Contact", 7, 3);
			
			//step 2:navigate to Organization module
			hp.getOrgLink().click();
			
			//step 3:click on "Create Organizations" Button
			OrganizationsPage op=new OrganizationsPage(driver);
			op.getCreateNewOrgBtn().click();
			
			//step 4:enter all the details & create new organization
			CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
			cnop.createOrg(orgName);
			
//			String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//			if(headerInfo.contains(orgName))
//			{
//				System.out.println(orgName+" is created ==PASS ");
//			}else {
//				System.out.println(orgName+" is not created ==FAIL");
//			}
			
			//Step 5:Navigate to contact Module
			Thread.sleep(2000);
			hp.getContactLink().click();
			
			//step 6:click on "Create Contact" Button
			
			ContactPage cp=new ContactPage(driver);
			cp.getGetCreateContactBtn().click();
			
			//step 7:enter lastname & add a organization
			
			CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
			cncp.getLastname().sendKeys(contactLastName);;
			
		
			Thread.sleep(2000);
			cncp.getOrgPlusBtn().click();
			
			Thread.sleep(2000);
			wLib.switchToTabOnURL(driver,"module=Accounts");
			
			SelectOrganizationPage sop=new SelectOrganizationPage(driver);
			sop.getSearchOrgEdt().sendKeys(orgName+Keys.ENTER);
			
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			
			wLib.switchToTabOnURL(driver,"module=Contacts");
			
			cncp.getSaveBtn().click();
			
			//verify Header msg Expected Result
			
			 String actHeader =cp.getHeaderMsg().getText();
			boolean status=actHeader.contains(contactLastName);
			
			Assert.assertEquals(status, true);
	
			String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
			SoftAssert soft=new SoftAssert();
			soft.assertEquals(actLastName, contactLastName);
			soft.assertAll();
		
			//verify Header orgName info Expected Result
			String actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
			
			if(actOrgName.contains(orgName))
			{
				System.out.println(orgName+" is created ==>PASS ");
			}else {
				System.out.println(orgName+" is not created ==>FAIL");
			}
		}
		

}



