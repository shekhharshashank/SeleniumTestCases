package qaautomation.uitests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.Test;

import qaautomation.core.BasesPageUITest;
import qaautomation.pageobjects.CleartripHomePage;
import qaautomation.utils.SetupUtilities;

public class CleartripScenarioLoginTests extends BasesPageUITest {

	public static final String USE_CASES = "loginDetails";

	@Test(alwaysRun = true, description = "Positive Tests")
	public void scenario1() throws Exception {

		Reporter.log("Navigate to CLearTrip HomePage");
		cleartripHomePage = new CleartripHomePage(uidriver);

		Reporter.log(" Happy Path ");
		repeatSteps(SetupUtilities.loadDataFromCsv("T1", USE_CASES));

		Reporter.log("Get logged User Details");
		assertEquals(clearTripSuccessLoginPage.getloggedIntipText(), SetupUtilities.getPropertyValue(USER_EMAIL));

		Reporter.log("Get logged User Details");
		clearTripSuccessLoginPage.clickyourTripsBtn();
		assertEquals(clearTripSuccessLoginPage.getloggedInEmail(), SetupUtilities.getPropertyValue(USER_EMAIL));

	}

	@Test(alwaysRun = true, description = "Negative Scenarios")
	public void scenario2() throws Exception {

		Reporter.log("Navigate to CLearTrip HomePage");
		cleartripHomePage = new CleartripHomePage(uidriver);

		Reporter.log("When Login Password Is Wrong It should Display Incorrect Password");
		repeatSteps(SetupUtilities.loadDataFromCsv("T2", USE_CASES));
		assertTrue(cleartripHomePage.isIncorrectPasswordMsgpresent(), "Msg Not Present");
		assertEquals(cleartripHomePage.getSigninErrorMsg(), SetupUtilities.getPropertyValue("signInErrorMsg"));
		assertEquals(cleartripHomePage.getIncorrectPasswordMsg(),
				SetupUtilities.getPropertyValue("inCorrectPassowrdMsg"));

		Reporter.log("Close The Pop up");
		cleartripHomePage = cleartripHomePage.closePopUp();

		Reporter.log("When Login Email Is Wrong It should Display Incorrect Email");
		repeatSteps(SetupUtilities.loadDataFromCsv("T3", USE_CASES));
		assertTrue(cleartripHomePage.isIncorrectEmailMsgpresent(), "Msg Not Present");
		assertTrue(cleartripHomePage.isIncorrectPasswordMsgpresent(), "Msg Not Present");
		assertEquals(cleartripHomePage.getSigninErrorMsg(), SetupUtilities.getPropertyValue("signInErrEmailMsg"));
		assertEquals(cleartripHomePage.getIncorrectPasswordMsg(),
				SetupUtilities.getPropertyValue("inCorrectPassowrdMsg"));

		Reporter.log("Close The Pop up");
		cleartripHomePage = cleartripHomePage.closePopUp();

		Reporter.log("When Login Email & Password Both Is Wrong It should Display Incorrect Email");
		repeatSteps(SetupUtilities.loadDataFromCsv("T4", USE_CASES));
		assertTrue(cleartripHomePage.isIncorrectEmailMsgpresent(), "Msg Not Present");
		assertTrue(cleartripHomePage.isIncorrectPasswordMsgpresent(), "Msg Not Present");
		assertEquals(cleartripHomePage.getSigninErrorMsg(), SetupUtilities.getPropertyValue("signInErrEmailMsg"));
		assertEquals(cleartripHomePage.getIncorrectPasswordMsg(),
				SetupUtilities.getPropertyValue("inCorrectPassowrdMsg"));
		assertEquals(cleartripHomePage.getIncorrectEmailMsg(), SetupUtilities.getPropertyValue("validEmailMsg"));

		Reporter.log("Close The Pop up");
		cleartripHomePage = cleartripHomePage.closePopUp();

		Reporter.log("When Login Email is Incorrect format");
		repeatSteps(SetupUtilities.loadDataFromCsv("T5", USE_CASES));
		assertEquals(cleartripHomePage.getIncorrectEmailMsg(), SetupUtilities.getPropertyValue("validEmailMsg"));

		Reporter.log("Close The Pop up");
		cleartripHomePage = cleartripHomePage.closePopUp();


	}

	private void repeatSteps(Map<String, String> loginMap) {

		Reporter.log("Click Your Trips Btn");
		cleartripHomePage.clickyourTripsBtn();

		Reporter.log("Click SignIn Btn");
		cleartripHomePage.clickSignInBtn();

		Reporter.log("Enter Email");
		cleartripHomePage.enterEmailAddress(loginMap.get(USER_EMAIL));

		Reporter.log("Enter Password");
		cleartripHomePage.enterPassword(loginMap.get(USER_PASS));

		Reporter.log("Forgot Link");
		assertTrue(cleartripHomePage.isForgotLinkPresent(),"ForGot Link");

		Reporter.log("Click Sign In");
		cleartripHomePage.clickSignInBtnInPopup();
	}
}
