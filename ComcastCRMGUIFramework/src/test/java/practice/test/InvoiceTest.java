package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.Baseclass;



@Listeners(com.comcast.crm.listenerUtility.ListenerImplementationClass.class)
public class InvoiceTest extends Baseclass{
	
	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListenerImplementationClass.class)
	public void createInvoiceTest()
	{
		System.out.println("Execute Create Invoice Test");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
//	@Test
//	public void createInvoicewithContactTest()
//	{
//		System.out.println("Execute Create Invoice with contact Test");
//		System.out.println("step-1");
//		System.out.println("step-2");
//		System.out.println("step-3");
//		System.out.println("step-4");
//	}

}
