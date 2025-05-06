package practice.test;
/**
 * test class for contact module
 * @author Sanjana
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.Baseclass;
import com.comcast.crm.objectrepositaryutility.LoginPage;

public class SearchContactTest extends Baseclass{
	/**
	 * Scenario :login()==>
	 */
	
	@Test
	public void searchcontactTest()
	{
		/*step1: login to app*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp("url", "username","pass");
	}
}
