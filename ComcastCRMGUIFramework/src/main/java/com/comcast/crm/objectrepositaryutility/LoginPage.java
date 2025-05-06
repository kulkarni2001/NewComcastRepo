package com.comcast.crm.objectrepositaryutility;

/**
 * @author HP
 * 
 * contains Login page elements & business lib like login()
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {//Rule-1 create a separte java class
						//Rule-2 Object Creation
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
				//Rule-3:Object Initialization

	//Rule-4:Object Encapsulation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	//Rule-5: provide  Action
	/**
	 * login to application based on username,password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToapp(String url,String username,String password)
	{
		driver.manage().window().maximize();
		waitForPageLoad(driver);
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
		
	}
}
