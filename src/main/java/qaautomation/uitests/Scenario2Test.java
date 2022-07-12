package qaautomation.uitests;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import qaautomation.core.BasesPageUITest;
import qaautomation.pageobjects.MMTFlightPage;
import qaautomation.pageobjects.MMTFlightSearchPage;
import qaautomation.pageobjects.MMTHomePage;
import qaautomation.pageobjects.MMTHotelPage;
import qaautomation.utils.SetupUtilities;

@SuppressWarnings("static-access")
public class Scenario2Test extends BasesPageUITest {

	public MMTHomePage mMTHomePage;
	public MMTFlightPage mMTFlightPage;
	public MMTHotelPage mMTHotelPage;
	public MMTFlightSearchPage mMTFlightSearchPage;
	
	
	@Test(alwaysRun=true)
	public void example2() throws InterruptedException, IOException {
	
		String userName=SetupUtilities.getPropertyValue("userName");
		String password=SetupUtilities.getPropertyValue("userPass");
		
		
		
		reporter.log("Navigate to MMT HomePage");
		mMTHomePage= new MMTHomePage(uidriver);
		
		reporter.log("Close the Pop Up If Present");
		mMTHomePage.closePopupIfPresent();
		
		reporter.log("Navigate to Login Page");
		mMTHomePage.clickOnLoginButton();
		
		
		reporter.log("Enter The User Name");
		mMTHomePage.enterUserName(userName);
		
		
		reporter.log("Enter The User Name");
		mMTHomePage.enterPassword(password);
		
		
	}
	
	
}
