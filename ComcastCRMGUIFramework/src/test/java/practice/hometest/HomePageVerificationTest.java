package practice.hometest;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePageVerificationTest {
	
	@Test
	public void homepage(Method mtd) throws InterruptedException
	{
		System.out.println(mtd.getName()+" Test Start");
		String expectedPage="Home";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String actTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		
		Assert.assertEquals(actTitle, expectedPage);
//		if(actTitle.trim().equals(expectedPage))
//		{
//			System.out.println(expectedPage+"page is verified==>PASS ");
//		}else
//		{
//			System.out.println(expectedPage+"page is not verified==>FAIL ");
//		}
		
		System.out.println(mtd.getName()+"Test End");
		
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void VerifyLogoHomePageTest(Method mtd) throws InterruptedException
	{
		System.out.println(mtd.getName()+"Test Start");
		String expectedPage="Home";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		
		Assert.assertTrue(status);
//		if(status)
//		{
//			System.out.println("Logo is Verified==>PASS");
//		}else
//		{
//			System.out.println("Logo is not Verified==>FAIL");
//		}
		System.out.println(mtd.getName()+"Test END");
		Thread.sleep(2000);
		driver.quit();
	}

}
