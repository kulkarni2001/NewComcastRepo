package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerUtility.ListenerImplementationClass;
import com.comcast.crm.objectrepositaryutility.ContactPage;
import com.comcast.crm.objectrepositaryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.objectrepositaryutility.SelectOrganizationPage;

/**
 *@author HP
 */

@Listeners(com.comcast.crm.listenerUtility.ListenerImplementationClass.class)
public class CreateContactTest extends Baseclass {

	@Test
	public void createContactTest() throws IOException, InterruptedException {
		Assert.fail();
		UtilityClassObject.getTest().log(Status.INFO,"Read data from Excel");
		// read testScript data from Excel File

		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();
		// step 2:navigate to Contacts module
		ListenerImplementationClass.test.log(Status.INFO,"Navigate to Contact page");
		hp.getContactLink().click();

		// step 3:click on "Create Contact" Button
		ListenerImplementationClass.test.log(Status.INFO,"Navigate to create Contact page");

		ContactPage cp = new ContactPage(driver);
		cp.getGetCreateContactBtn().click();
		// step 4:enter all the details & create new organization
	//org name
		

		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName);
		UtilityClassObject.getTest().log(Status.INFO,lastName+"create a contact using lastName ");


		// verify last name Result

		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actualLastName.equals(lastName)) {
			System.out.println(lastName + "  information is verified ==>PASS ");
		} else {
			System.out.println(lastName + "  information is not verified ==>FAIL");
		}

	}
	
	@Test
	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
		//read testScript data from Excel File
		
		String lastName=eLib.getDataFromExcel("Contact", 4, 2)+jLib.getRandomNumber();
		//navigate to contact module
	
		hp.getContactLink().click();
		//click on create contact button 
		ContactPage cp=new ContactPage(driver);
		cp.getGetCreateContactBtn().click();
		
		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYMMDD(30);
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		CreatingNewContactPage ccp=new CreatingNewContactPage(driver);
		ccp.createContactWithSupportDate(lastName, startDate, endDate);
//		wLib.switchToAlertandAccept(driver);
		Thread.sleep(5000);
		
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
		
	}
	

	@Test
	public void createContactwithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException
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
		
		String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName))
		{
			System.out.println(orgName+" is created ==PASS ");
		}else {
			System.out.println(orgName+" is not created ==FAIL");
		}
		
		//Step 5:Navigate to contact Module
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
	}
	
	}
