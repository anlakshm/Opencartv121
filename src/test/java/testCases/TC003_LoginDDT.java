package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT  extends BaseClass{
	
	@Test(dataProvider ="LoginData", dataProviderClass = DataProviders.class,groups="Datadriven")
	public void verify_LoginDDT(String email, String pwd,String exp)
	{
		try 
		{
			logger.info("**********Started TC003_LoginDDT*****************");
			//HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//Login
			LoginPage lp = new LoginPage(driver);
			lp.setEmailAddress(email);
			lp.setPassword(pwd);
			lp.clickLoginButton();
			
			//MyAccount
			MyAccountPage macc = new MyAccountPage(driver);
			boolean target_page = macc.isMyAccountExist();
			
			// Data is valid -----> login successful --test passed-- logout
			//                      login unsuccessful -- test failed 
			
			//Data is invalid -----> login successful -- test failed --logout
			//                       login unsuccessful -- test passed 
			
			if(exp.equalsIgnoreCase("valid"))
			{
				if(target_page==true)
				{
					macc.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(target_page==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******************finished TC003_LoginDDT****************");
		
	}
}
