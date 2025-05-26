package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;



public class TC001_AccountRegistrationTest extends BaseClass

{
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		try
		{
			logger.info("****Starting TC001_AccountRegistrationTest *******");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on Myaccount link");
			hp.clickRegister();
			logger.info("clicked on register link");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("providing customer info...........");
			regpage.setFirstname(randomString());
			regpage.setLastname(randomString());
			regpage.setEmail(randomString()+"@gmail.com");
			regpage.setTelephone(randomNumber());
			
			String alphanum=randomAlphaNumeric();
			regpage.setPassword(alphanum);
			regpage.setConfirmPassword(alphanum);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			
			logger.info("validating expected message");
			String confmsg =regpage.getMsgConfirmation();
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test failed...........");
				logger.debug("Debug logs");
				Assert.assertTrue(false);
			
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****finished TC001_AccountRegistrationTest *******");
	}
	

}
