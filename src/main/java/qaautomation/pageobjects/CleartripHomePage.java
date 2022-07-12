package qaautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qaautomation.core.BasePage;

public class CleartripHomePage extends BasePage {

	public CleartripHomePage(WebDriver uidriver) {
		super(uidriver);
		// TODO Auto-generated constructor stub
	}

	private By homePageLogo = By.xpath("//a[@data-test-attrib='cleartrip-logo']");
	private By flightOptions = By.cssSelector("li.humbger a[href='/flights']");
	private By hotelOptions = By.cssSelector("li.mt-2 a[href='/hotel']");
	private By yourTripsBtn = By.cssSelector("div[class='p-relative'] button");
	private By signInBtn = By.cssSelector("div[class^='bg-white br'] button");
	private By emailAddressBtn = By.cssSelector("div[class^='bg-white br'] input[data-testid='email']");
	private By passwordBtn = By.cssSelector("div[class^='bg-white br'] input[data-testid='password']");
	private By forgotPassBtn = By.cssSelector("div[class^='bg-white br'] span[data-testid='forgot']");
	private By signInErrorMsg = By.cssSelector("span[data-testid='signinError']");
	private By popupSignInBgtn = By.cssSelector("div[class^='bg-white br'] button");
	private By inCorrectPasswordErrorMsg = By.xpath("//*[contains(text(),'Incorrect password')]");
	private By inCorrectEmailMsg = By.cssSelector("div [data-testid='invalidEmailMessage']");
	private By closePopUpBtn=By.cssSelector("[class='px-8'] [class^='br-4']");

	public CleartripHomePage closePopUp() {
		createExplicitWaitClickable(closePopUpBtn, 10);
		uidriver.findElement(closePopUpBtn).click();
		return new CleartripHomePage(uidriver);
	}

	public String getIncorrectPasswordMsg() {

		return uidriver.findElement(inCorrectPasswordErrorMsg).getText();

	}
	public String getIncorrectEmailMsg() {

		return uidriver.findElement(inCorrectEmailMsg).getText();

	}

	public boolean isIncorrectPasswordMsgpresent() {
		createExplicitWait(inCorrectPasswordErrorMsg, 8);
		return uidriver.findElement(inCorrectPasswordErrorMsg).isDisplayed();
	}

	public boolean isIncorrectEmailMsgpresent() {
		return uidriver.findElement(inCorrectEmailMsg).isDisplayed();
	}

	public boolean isSignInErrorMsgpresent() {

		return uidriver.findElement(signInErrorMsg).isDisplayed();

	}

	public String getSigninErrorMsg() {

		return uidriver.findElement(signInErrorMsg).getText();

	}

	public ClearTripSuccessLoginPage clickSignInBtnOnPopup() {

		uidriver.findElement(popupSignInBgtn).click();
		uidriver.findElement(popupSignInBgtn).click();
		return new ClearTripSuccessLoginPage(uidriver);
	}

	public void clickSignInBtnInPopup() {

		uidriver.findElement(popupSignInBgtn).click();
		createExplicitWaitClickable(popupSignInBgtn, 10);

	}

	public boolean isForgotLinkPresent() {

		return uidriver.findElement(forgotPassBtn).isDisplayed();
	}

	public void enterPassword(String password) {

		uidriver.findElement(passwordBtn).sendKeys(password);
	}

	public void enterEmailAddress(String email) {

		uidriver.findElement(emailAddressBtn).sendKeys(email);
	}

	public void clickSignInBtn() {

		uidriver.findElement(signInBtn).click();

	}

	public void clickyourTripsBtn() {

		uidriver.findElement(yourTripsBtn).click();

	}

	public boolean isLogoPresent() {
		return uidriver.findElement(homePageLogo).isDisplayed();
	}

	public boolean isFlightsOptnPresent() {
		return uidriver.findElement(flightOptions).isDisplayed();
	}

	public boolean isHotelsOptnPresent() {
		return uidriver.findElement(hotelOptions).isDisplayed();
	}

	public CleartripFlightPage navigateToFlightsPage() {
		uidriver.findElement(flightOptions).click();
		return new CleartripFlightPage(uidriver);
	}

}
