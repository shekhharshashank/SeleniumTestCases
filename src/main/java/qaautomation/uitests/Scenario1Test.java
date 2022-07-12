package qaautomation.uitests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import qaautomation.core.BasesPageUITest;
import qaautomation.pageobjects.MMTFlightPage;
import qaautomation.pageobjects.MMTFlightSearchPage;
import qaautomation.pageobjects.MMTHomePage;
import qaautomation.pageobjects.MMTHotelPage;
import qaautomation.utils.SetupUtilities;

public class Scenario1Test extends BasesPageUITest  {

	public MMTHomePage mMTHomePage;
	public MMTFlightPage mMTFlightPage;
	public MMTHotelPage mMTHotelPage;
	public MMTFlightSearchPage mMTFlightSearchPage;
	
	
	@Test(alwaysRun=true)
	public void example1() throws InterruptedException {
		
		uidriver.get("https://www.makemytrip.com");
		mMTHomePage= new MMTHomePage(uidriver);
	
		Reporter.log("Navigate to FLights Page");
		mMTFlightPage=mMTHomePage.navigateToGetFlightsPage();
		
		
		mMTFlightPage.removePopUpIfPresent();
		mMTFlightPage.refrehPage();
		mMTFlightPage.selectRoundTrip();

		
		mMTFlightPage.selectFromCity("Goa");
		mMTFlightPage.selectToCity("Mumbai");
		
		String dateRequired=SetupUtilities.getNDaysfromToday("E MMM dd yyyy",4);
		String dateReturn=SetupUtilities.getNDaysfromToday("E MMM dd yyyy",5);
		System.out.println(dateRequired +"   "+dateReturn);
		mMTFlightPage.chooseDate(dateRequired,dateReturn);
		
		mMTFlightPage.selecTravellers();
		mMTFlightPage.chooseNumberofAdults("1");
		mMTFlightPage.chooseNumberofChildren("1");
		mMTFlightPage.chooseClassofFLight("Economy/Premium Economy");
		mMTFlightPage.applyButton();
		
		mMTFlightSearchPage=mMTFlightPage.searchButton();
		
		waitSeconds(10);
		mMTFlightSearchPage.selectstartFlight();
		mMTFlightSearchPage.selectreturnFlight();
		mMTFlightSearchPage.bookNowchoosenFlights();
		
		waitSeconds(20);
		
		
	}
	

	
}
