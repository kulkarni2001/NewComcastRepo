package com.comcast.crm.objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectOrganizationPage {

	WebDriver driver;
	public SelectOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="search_text")
	private WebElement searchOrgEdt;
	
	public WebElement getSearchOrgEdt() {
		return searchOrgEdt;
	}
	
//	@FindBy(xpath = "//a[text()='"+orgName+"']")
//	private WebElement selectOrg;
//	
	
}
