package com.comcast.crm.objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OrganizationsPage {
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;

	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	@FindBy(id="search_txt")
	private WebElement searchOrg;
	
	@FindBy(name="search_field")
	private WebElement searchOrgDD;
	
	
	
	
	public WebElement getSearchOrg() {
		return searchOrg;
	}

	
	

	public WebElement getSearchOrgDD() {
		return searchOrgDD;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}
	
	
	

}
