package qaautomation.uitests;

import static org.testng.Assert.assertTrue;

import org.testng.Reporter;
import org.testng.annotations.Test;

import qaautomation.core.BasesPageUITest;
import qaautomation.pageobjects.CleartripHomePage;
import qaautomation.utils.SetupUtilities;

public class CleartripScenarioBookFlightTest extends BasesPageUITest {

	public static final String USE_CASES = "flightDetails";
	public static final String BOARDING_CTY="boardingCity";
	public static final String DESTINATION_CITY="destinationCity";

	@Test(alwaysRun = true, description = "Positive Tests")
	public void bookFlights() throws Exception {

		Reporter.log("Navigate to CLearTrip HomePage");
		cleartripHomePage = new CleartripHomePage(uidriver);

		assertTrue(cleartripHomePage.isFlightsOptnPresent(),"Not Present");
		assertTrue(cleartripHomePage.isHotelsOptnPresent(),"Hotel Not Present");
		assertTrue(cleartripHomePage.isLogoPresent(),"Logo Not Present");

		Reporter.log("Navigate to Flights Page");
		cleartripFlightPage=cleartripHomePage.navigateToFlightsPage();

		Reporter.log("Present ");
		cleartripFlightPage.selectTripType("round");

		cleartripFlightPage.selectAndenterBoardingCity(SetupUtilities.loadDataFromCsv("T1", USE_CASES).get(BOARDING_CTY));

		cleartripFlightPage.selectAndenterDestinationCity(SetupUtilities.loadDataFromCsv("T1", USE_CASES).get(DESTINATION_CITY));

		String dateRequired=SetupUtilities.getNDaysfromToday("E MMM dd yyyy",4);
		String dateReturn=SetupUtilities.getNDaysfromToday("E MMM dd yyyy",5);

		cleartripFlightPage.selectDates(dateRequired,dateReturn);

		System.out.println(dateRequired);





		waitSeconds(100);

	}

}
