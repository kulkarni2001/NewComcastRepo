package com.comcast.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositaryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositaryutility.HomePage;
import com.comcast.crm.objectrepositaryutility.LoginPage;
import com.comcast.crm.objectrepositaryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositaryutility.OrganizationsPage;

public class DeleteOrgTest {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver=null;

			FileUtility fLib=new FileUtility();
			ExcelUtility eLib=new ExcelUtility();
			JavaUtility jLib=new JavaUtility();
			WebDriverUtility wLib=new WebDriverUtility();
			
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
				
				String orgName=eLib.getDataFromExcel("Org", 10, 2)+jLib.getRandomNumber();
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(URL);
				
				LoginPage lp = new LoginPage(driver);
				lp.loginToapp(URL, Username, Password);
				
				//step 2:navigate to Organization module
				
				HomePage hp=new HomePage(driver);
				hp.getOrgLink().click();
				
				//step 3:click on "Create Organizations" Button
				
				OrganizationsPage op=new OrganizationsPage(driver);
				op.getCreateNewOrgBtn().click();
				
				//step 4:enter all the details & create new organization
				CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
				cnop.createOrg(orgName);
				
				//verify Header msg Expected Result

				OrganizationInfoPage oip=new OrganizationInfoPage(driver);
				String actOrgName=oip.getHeaderMsg().getText();
				
				if(actOrgName.contains(orgName)) {
					System.out.println(orgName+" name is verified ==>PASS");
				}else
				{
					System.out.println(orgName+" name is not verified==>FAIL");
				}
				
				//go back to organization page
				
				hp.getOrgLink().click();
				//search for organization 
				
				op.getSearchEdt().sendKeys(orgName);
				
				//in dynamic webtable select and delete org
				
				wLib.select(op.getSearchDD(), "Organization Name");
				op.getSearchBtn().click();
				
				//for handling dynamic elements we have to go for findelement 
				driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();

				wLib.switchToAlertandAccept(driver);
				
				hp.logout();

				Thread.sleep(2000);
				driver.quit();
	}
}
