package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitForElementClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public void switchToTabOnURL(WebDriver driver,String  partialURL)
	{
		Set<String> id = driver.getWindowHandles();
		for (String string : id) {
			driver.switchTo().window(string);
			@Nullable
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains(partialURL))
			{
				break;
			}
			
		}
		
	}
	
	public void switchToTabOnTitle(WebDriver driver,String  partialURL)
	{
		Set<String> id = driver.getWindowHandles();
		for (String string : id) {
			driver.switchTo().window(string);
			@Nullable
			String actUrl = driver.getTitle();
			if(actUrl.contains(partialURL))
			{
				break;
			}	
		}	
	}
	
	public void switchtoFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchtoFrame(WebDriver driver,String nameID)
	{
		driver.switchTo().frame(nameID);
	}
	public void switchtoFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertandAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertandCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}

	public void select(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}

	public void mouseMoveOnElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void doubleclick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
}
