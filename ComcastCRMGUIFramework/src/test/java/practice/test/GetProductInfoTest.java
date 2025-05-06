package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;



public class GetProductInfoTest {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String ProductName) throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		Thread.sleep(10000);
		//search a product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		String x="//span[text()='"+ProductName+"']/../../../../div[3]/div/div/div[1]/div[1]/div/a/span/span[2]";
		
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		Thread.sleep(2000);
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility elib=new ExcelUtility();
		int rowcount=elib.getRowCount("Product");
		Object[][] objArr=new Object[rowcount][2];
		
		
		for(int i=0;i<rowcount;i++)
		{
			objArr[i][0]=elib.getDataFromExcel("Product", i+1,0);
			objArr[i][1]=elib.getDataFromExcel("Product", i+1,1);
			System.out.println(i+1);
		}
		
		return objArr;
	}

}
