package com.comcast.crm.objectrepositaryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(name="support_end_date")
	private WebElement endDateEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgplusbtn;
	
	
	
	

	public WebElement getOrgPlusBtn() {
		return orgplusbtn;
	}

	public WebElement getLastname() {
		return lastnameEdt;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	
	public WebElement getStartDate() {
		return startDateEdt;
	}

	public WebElement getEndDate() {
		return endDateEdt;
	}

	public void createContact(String lastname)
	{
		lastnameEdt.sendKeys(lastname);
		saveBtn.click();
		
	}
	
	public void createContactWithSupportDate(String lastname,String startDate,String endDate)
	{
		lastnameEdt.sendKeys(lastname);
		startDateEdt.clear();
		startDateEdt.sendKeys(startDate);
		endDateEdt.clear();
		endDateEdt.sendKeys(endDate);
		saveBtn.click();
			
	}
	

}
