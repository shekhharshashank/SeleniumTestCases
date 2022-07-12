package qaautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaautomation.core.BasePage;

public class MMTHotelPage extends BasePage {

	public static final String HOTELS="hotels";
	
	public MMTHotelPage(WebDriver uidriver) {
		super(uidriver);
	}
	
	public MMTFlightPage navigateToGetFLightsPage() {
		navigateToPage(FLIGHTS);
		return new MMTFlightPage(uidriver);
	}
	
}
