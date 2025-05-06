package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.objectrepositaryutility.ContactPage;
import com.comcast.crm.objectrepositaryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositaryutility.OrganizationsPage;
import com.comcast.crm.objectrepositaryutility.SelectOrganizationPage;

public class DemoTest extends Baseclass {
	
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
